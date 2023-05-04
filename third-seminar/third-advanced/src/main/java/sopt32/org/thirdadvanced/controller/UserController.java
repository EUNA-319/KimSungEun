package sopt32.org.thirdadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sopt32.org.thirdadvanced.controller.dto.common.ApiResponseDto;
import sopt32.org.thirdadvanced.controller.dto.request.UserRequestDto;
import sopt32.org.thirdadvanced.controller.dto.response.UserResponseDto;
import sopt32.org.thirdadvanced.exception.SuccessStatus;
import sopt32.org.thirdadvanced.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponseDto.success(SuccessStatus.SIGNUP_SUCCESS, userService.create(request));
    }
}
