package sopt.org.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.sixthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.sixthSeminar.domain.Board;
import sopt.org.sixthSeminar.domain.User;
import sopt.org.sixthSeminar.exception.Error;
import sopt.org.sixthSeminar.exception.model.NotFoundException;
import sopt.org.sixthSeminar.infrastructure.BoardRepository;
import sopt.org.sixthSeminar.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(Long userId, BoardRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Board newBoard = Board.newInstance(
                user,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        boardRepository.save(newBoard);
    }
}
