package sopt.org.eighthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sopt.org.eighthSeminar.domain.SocialPlatform;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SocialServiceProvider {

    private static final Map<SocialPlatform, SocialService> socialServiceMap = new HashMap<>();

    private final KakaoSocialService kakaoSocialService;

    @PostConstruct
    void initializeSocialServiceMap() {
        socialServiceMap.put(SocialPlatform.KAKAO, kakaoSocialService);
        // 만약 구글이 추가된다면?
        // socialServiceMap.put(SocialPlatform.GOOGLE, googleSocialService);
    }

    public SocialService getSocialService(SocialPlatform socialPlatform) {
        return socialServiceMap.get(socialPlatform);
    }
}
