package sopt.org.seventhSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.seventhSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.UserRequestDto;
import sopt.org.seventhSeminar.controller.dto.response.UserResponseDto;
import sopt.org.seventhSeminar.domain.User;
import sopt.org.seventhSeminar.exception.Error;
import sopt.org.seventhSeminar.exception.model.BadRequestException;
import sopt.org.seventhSeminar.exception.model.ConflictException;
import sopt.org.seventhSeminar.exception.model.NotFoundException;
import sopt.org.seventhSeminar.infrastructure.UserRepository;

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

        if (!user.verifyUserPassword(request.getPassword())) {
            throw new BadRequestException(Error.INVALID_PASSWORD_EXCEPTION, Error.INVALID_PASSWORD_EXCEPTION.getMessage());
        } // User Entity 내부에 this.password랑 비교하는 로직으로 빼주는 것이 더 좋을 것 같음!
        // 서비스 계층은 하나의 로직을 시각화 하는 역할인데, 이런 식으로 서비스에 도메인 로직을 실행하게 되면 코드가 오히려 가독성이 떨어진다
        // 이런 유저의 비밀번호와 요청으로 들어온 비밀번호가 같은지 비교하는 로직은 로그인 외에도 비밀번호 변경 로직에도 사용할 수 있기 때문이다

        return user.getId();
    }

}
