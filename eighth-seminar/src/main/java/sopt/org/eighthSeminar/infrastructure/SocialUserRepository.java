package sopt.org.eighthSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.eighthSeminar.domain.SocialUser;

public interface SocialUserRepository extends Repository<SocialUser, Long> {

    // CREATE
    void save(SocialUser socialUser);
}
