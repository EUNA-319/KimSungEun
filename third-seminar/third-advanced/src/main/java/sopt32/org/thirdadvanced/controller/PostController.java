package sopt32.org.thirdadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import sopt32.org.thirdadvanced.controller.dto.common.ApiResponseDto;
import sopt32.org.thirdadvanced.controller.dto.request.PostRequestDto;
import sopt32.org.thirdadvanced.controller.dto.response.PostResponseDto;
import sopt32.org.thirdadvanced.exception.SuccessStatus;
import sopt32.org.thirdadvanced.service.PostService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<PostResponseDto> save(@RequestBody @Valid final PostRequestDto request) {
        return ApiResponseDto.success(SuccessStatus.POST_SAVE_SUCCESS, postService.save(request));
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<PostResponseDto> findPostById(@PathVariable final Long postId) {
        return ApiResponseDto.success(SuccessStatus.POST_GET_SUCCESS, postService.findPostById(postId));
    }
}
