package sopt32.org.thirdadvanced.infrastructure;

import org.springframework.data.repository.Repository;
import sopt32.org.thirdadvanced.domain.User;

public interface UserRepository extends Repository<User, Long> {
    void save(User user); // 유저 회원 가입
    User findById(Long user_id);
}