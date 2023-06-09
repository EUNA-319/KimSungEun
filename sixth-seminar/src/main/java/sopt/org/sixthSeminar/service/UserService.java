package sopt.org.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.sixthSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.sixthSeminar.controller.dto.request.UserRequestDto;
import sopt.org.sixthSeminar.controller.dto.response.UserResponseDto;
import sopt.org.sixthSeminar.domain.User;
import sopt.org.sixthSeminar.exception.model.BadRequestException;
import sopt.org.sixthSeminar.exception.model.ConflictException;
import sopt.org.sixthSeminar.exception.model.NotFoundException;
import sopt.org.sixthSeminar.infrastructure.UserRepository;
import sopt.org.sixthSeminar.exception.Error;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto create(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(Error.ALREADY_EXIST_USER_EXCEPTION, Error.ALREADY_EXIST_USER_EXCEPTION.getMessage());
        }

        User newUser = User.newInstance(
                request.getNickname(),
                request.getEmail(),
                request.getPassword()
        );

        userRepository.save(newUser);

        return UserResponseDto.of(newUser.getId(), newUser.getNickname());
    }

    @Transactional
    public Long login(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException(Error.INVALID_PASSWORD_EXCEPTION, Error.INVALID_PASSWORD_EXCEPTION.getMessage());
        }

        return user.getId();
    }

}
