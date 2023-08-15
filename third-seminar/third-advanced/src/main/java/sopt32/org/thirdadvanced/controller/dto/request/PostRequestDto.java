package sopt32.org.thirdadvanced.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class PostRequestDto {
    private Long userId;
    @NotEmpty // 입력받는 제목과 내용은 입력이 없을 시 저장되지 않는다
    private String title;
    @NotEmpty
    private String content;
}
