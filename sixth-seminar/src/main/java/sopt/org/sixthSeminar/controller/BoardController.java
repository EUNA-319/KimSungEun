package sopt.org.sixthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.sixthSeminar.common.dto.ApiResponse;
import sopt.org.sixthSeminar.config.resolver.UserId;
import sopt.org.sixthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.sixthSeminar.exception.Success;
import sopt.org.sixthSeminar.service.BoardService;

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
