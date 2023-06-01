package sopt.org.sixthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.sixthSeminar.common.dto.ApiResponse;
import sopt.org.sixthSeminar.config.jwt.JwtService;
import sopt.org.sixthSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.sixthSeminar.controller.dto.request.UserRequestDto;
import sopt.org.sixthSeminar.controller.dto.response.UserLoginResponseDto;
import sopt.org.sixthSeminar.controller.dto.response.UserResponseDto;
import sopt.org.sixthSeminar.exception.Success;
import sopt.org.sixthSeminar.service.UserService;


import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponse.success(Success.SIGNUP_SUCCESS, userService.create(request));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) {
        final Long userId = userService.login(request);
        final String token = jwtService.issuedToken(String.valueOf(userId));
        return ApiResponse.success(Success.LOGIN_SUCCESS, UserLoginResponseDto.of(userId, token));
    }
}

