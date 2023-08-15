package sopt32.org.thirdadvanced.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(unique=true, nullable = false) // 중복된 이메일은 보통 서비스에서 사용할 수 없다
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder // 빌더 에노테이션
    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}