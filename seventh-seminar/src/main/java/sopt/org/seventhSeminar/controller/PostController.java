package sopt.org.seventhSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sopt.org.seventhSeminar.common.dto.ApiResponse;
import sopt.org.seventhSeminar.config.jwt.JwtService;
import sopt.org.seventhSeminar.config.resolver.UserId;
import sopt.org.seventhSeminar.controller.dto.request.PostDeleteRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.PostImageListRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.PostRequestDto;
import sopt.org.seventhSeminar.exception.Success;
import sopt.org.seventhSeminar.external.client.aws.S3Service;
import sopt.org.seventhSeminar.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final S3Service s3Service;
    private final PostService postService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
            @UserId Long userId,
            @ModelAttribute @Valid final PostRequestDto request) {
        String postThumbnailImageUrl = s3Service.uploadImage(request.getThumbnail(), "post"); // 경로를 가져온다
        postService.create(userId, postThumbnailImageUrl, request);
        return ApiResponse.success(Success.CREATE_POST_SUCCESS);
    }

    @PostMapping(value = "/create-imgs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@UserId Long userId, @ModelAttribute @Valid final PostImageListRequestDto request){
        List<String> postThumbnailImageUrlList = s3Service.uploadImages(request.getPostImages(),"post");
        postService.create(userId, postThumbnailImageUrlList, request);
        return ApiResponse.success(Success.CREATE_POST_SUCCESS);
    }

    @DeleteMapping(value = "/delete",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@Valid final PostDeleteRequestDto request){
        System.out.println(request.getImgUrl());
        s3Service.deleteFile(request.getImgUrl());
        return ApiResponse.success(Success.DELETE_IMAGE_SUCCESS);
    }
}
