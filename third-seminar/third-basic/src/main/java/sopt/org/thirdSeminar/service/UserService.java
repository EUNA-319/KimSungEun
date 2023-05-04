package sopt.org.thirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.thirdSeminar.controller.dto.request.UserRequestDto;
import sopt.org.thirdSeminar.controller.dto.response.UserResponseDto;
import sopt.org.thirdSeminar.domain.User;
import sopt.org.thirdSeminar.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional // 트랜잭션으로 묶어서 create 내의 sql 문을 모두 rollback
    public UserResponseDto create(UserRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return UserResponseDto.of(user.getId(), user.getNickname());
    }
}
