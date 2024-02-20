package sopt.org.seventhSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.seventhSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.seventhSeminar.domain.Board;
import sopt.org.seventhSeminar.domain.User;
import sopt.org.seventhSeminar.exception.Error;
import sopt.org.seventhSeminar.exception.model.NotFoundException;
import sopt.org.seventhSeminar.infrastructure.BoardRepository;
import sopt.org.seventhSeminar.infrastructure.UserRepository;

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
