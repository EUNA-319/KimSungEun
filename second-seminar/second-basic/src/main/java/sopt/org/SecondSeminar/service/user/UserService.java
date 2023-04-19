package sopt.org.SecondSeminar.service.user;

import org.springframework.stereotype.Service;
import sopt.org.SecondSeminar.controller.user.dto.request.RegisterRequestDto;
import sopt.org.SecondSeminar.domain.User;

import java.util.Optional;
import static sopt.org.SecondSeminar.SecondSeminarApplication.userList;

@Service
public class UserService {
    public Long register(RegisterRequestDto request){
        // 받아온 request 데이터를 토대로 실제 User 객체 생성
        User newUser = new User(request.getGender(), request.getName(), request.getContact(), request.getAge());

        // 데이터베이스에 저장
        userList.add(newUser);
        newUser.setId((long)userList.size());

        // 저장한 유저 아아디 값 반환
        return newUser.getId();
    }

    public Optional<User> getOne(Long userId){
        return userList.stream()
                .filter(user->user.getId().equals(userId))
                .findAny();
    }

    public Optional<User> search(String name){
        return userList.stream()
                .filter(user -> user.getName().equals(name)) // name(parameter)과 User의 이름이 같은지 확인
                .findAny(); // String에서 가장 먼저 탐색되는 요소를 리턴하며 하나라도 반환하게
    }
}
