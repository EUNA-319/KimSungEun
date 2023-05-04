package sopt32.org.thirdadvanced.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {

    private String nickname;
    private String title;
    private String content;

    public static PostResponseDto of (String nickname, String title, String content){
        return new PostResponseDto(nickname,title,content);
    }
}
