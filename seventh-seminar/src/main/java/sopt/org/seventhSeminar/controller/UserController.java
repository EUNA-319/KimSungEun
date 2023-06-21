package sopt.org.seventhSeminar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.seventhSeminar.common.dto.ApiResponse;
import sopt.org.seventhSeminar.config.jwt.JwtService;
import sopt.org.seventhSeminar.controller.dto.request.TokenReissuedRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.UserRequestDto;
import sopt.org.seventhSeminar.controller.dto.response.TokenReissuedResponseDto;
import sopt.org.seventhSeminar.controller.dto.response.UserLoginResponseDto;
import sopt.org.seventhSeminar.controller.dto.response.UserResponseDto;
import sopt.org.seventhSeminar.exception.Success;
import sopt.org.seventhSeminar.exception.Error;
import sopt.org.seventhSeminar.exception.model.UnauthorizedException;
import sopt.org.seventhSeminar.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="User", description = "유저 API Document")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="유저 생성 API", description = "유저를 서버에 등록합니다.")
    public ApiResponse<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponse.success(Success.SIGNUP_SUCCESS, userService.create(request));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 로그인 API", description = "유저가 서버에 로그인을 요청합니다.")
    public ApiResponse<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) {
        final Long userId = userService.login(request);
        final String accessToken = jwtService.issuedToken(String.valueOf(userId));
        final String refreshToken = jwtService.issuedRefreshToken(String.valueOf(userId));
        return ApiResponse.success(Success.LOGIN_SUCCESS, UserLoginResponseDto.of(userId, accessToken, refreshToken));
    }

    @PostMapping("/reissued") // 이 경우는 액세스 토큰이 만료되었을 경우에 refresh token을 통해 새로운 access token을 발급 받기 위해 사용한다
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 토큰 재발행 API", description = "유저가 서버에 토큰 재발행을 요청합니다.")
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

