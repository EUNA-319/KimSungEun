package sopt.org.seventhSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenReissuedRequestDto {
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
