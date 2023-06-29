package sopt.org.eighthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.eighthSeminar.common.dto.ApiResponse;
import sopt.org.eighthSeminar.controller.dto.request.SocialLoginRequestDto;
import sopt.org.eighthSeminar.exception.Success;
import sopt.org.eighthSeminar.service.SocialService;
import sopt.org.eighthSeminar.service.SocialServiceProvider;
import sopt.org.eighthSeminar.service.dto.request.SocialLoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialServiceProvider socialServiceProvider;

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestHeader("code") String code, @RequestBody SocialLoginRequestDto request) {
        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());
        return ApiResponse.success(Success.SOCIAL_LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(code)));
    }
}
