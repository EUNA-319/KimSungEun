package sopt.org.sixthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.sixthSeminar.common.dto.ApiResponse;
import sopt.org.sixthSeminar.config.jwt.JwtService;
import sopt.org.sixthSeminar.config.redis.RedisRepository;
import sopt.org.sixthSeminar.controller.dto.request.TokenReissuedRequestDto;
import sopt.org.sixthSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.sixthSeminar.controller.dto.request.UserRequestDto;
import sopt.org.sixthSeminar.controller.dto.response.TokenReissuedResponseDto;
import sopt.org.sixthSeminar.controller.dto.response.UserLoginResponseDto;
import sopt.org.sixthSeminar.controller.dto.response.UserResponseDto;
import sopt.org.sixthSeminar.domain.RefreshToken;
import sopt.org.sixthSeminar.exception.Error;
import sopt.org.sixthSeminar.exception.Success;
import sopt.org.sixthSeminar.exception.model.NotFoundException;
import sopt.org.sixthSeminar.exception.model.UnauthorizedException;
import sopt.org.sixthSeminar.service.UserService;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    @Autowired
    private final RedisRepository redisRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponse.success(Success.SIGNUP_SUCCESS, userService.create(request));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) {
        final Long userId = userService.login(request);
        final String accessToken = jwtService.issuedToken(String.valueOf(userId));
        final String refreshToken = jwtService.issuedRefreshToken(String.valueOf(userId));
        return ApiResponse.success(Success.LOGIN_SUCCESS, UserLoginResponseDto.of(userId, accessToken, refreshToken));
    }

    @PostMapping("/reissued") // 이 경우는 액세스 토큰이 만료되었을 경우에 refresh token을 통해 새로운 access token을 발급 받기 위해 사용한다
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TokenReissuedResponseDto> reissued(@RequestBody @Valid final TokenReissuedRequestDto request){
        // refresh token 만료 검증 -> refresh token 만료시 -> 다시 로그인해야한다
        if (!jwtService.verifyToken(request.getRefreshToken())) {
            throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
        }
        //만료가 안되었을 때 해당 refresh 토큰이 redis에 저장된 토큰과 같은지 확인 + access & refresh token을 재발급한다
        TokenReissuedResponseDto response = jwtService.reIssuedRefreshToken(request);

        return ApiResponse.success(Success.AUTHORIZATION_SUCCESS, response);
    }
}

