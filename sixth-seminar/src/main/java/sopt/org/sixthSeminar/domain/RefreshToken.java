package sopt.org.sixthSeminar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;


@Getter
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;
    private Long userId;
}
