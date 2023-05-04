package sopt32.org.thirdadvanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt32.org.thirdadvanced.controller.dto.request.UserRequestDto;
import sopt32.org.thirdadvanced.controller.dto.response.UserResponseDto;
import sopt32.org.thirdadvanced.domain.User;
import sopt32.org.thirdadvanced.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto create(UserRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return UserResponseDto.of(user.getId(),user.getNickname());
    }
}
