package sopt.org.seventhSeminar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.seventhSeminar.common.dto.ApiResponse;
import sopt.org.seventhSeminar.config.resolver.UserId;
import sopt.org.seventhSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.seventhSeminar.exception.Success;
import sopt.org.seventhSeminar.service.BoardService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name = "Board", description = "사진을 포함하지 않는 게시글 API Document")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "사진을 포함하지 않는 게시글 생성 API", description = "사진을 포함하지 않는 게시글을 서버에 등록합니다.")
    public ApiResponse create(
            @UserId Long userId,
            @RequestBody @Valid final BoardRequestDto request) {
        boardService.create(userId, request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }
}
