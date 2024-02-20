package sopt.org.seventhSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "토큰 요청 DTO")
public class TokenReissuedRequestDto {
    @NotNull
    @Schema(description = "액세스 토큰")
    private String accessToken;

    @NotNull
    @Schema(description = "리스레시 토큰")
    private String refreshToken;
}
