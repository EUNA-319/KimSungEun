package sopt.org.eighthSeminar.controller.dto.request;

import lombok.*;
import sopt.org.eighthSeminar.domain.SocialPlatform;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialLoginRequestDto {

    private SocialPlatform socialPlatform;
}
