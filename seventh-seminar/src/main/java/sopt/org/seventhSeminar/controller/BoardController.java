package sopt.org.seventhSeminar.controller;

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
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
            @UserId Long userId,
            @RequestBody @Valid final BoardRequestDto request) {
        boardService.create(userId, request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }
}
