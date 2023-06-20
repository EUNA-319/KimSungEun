package sopt.org.seventhSeminar.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter // ModelAttribute는 리스트를 자동 바인딩을 못해줍니다! 그래서 setter를 써준 것 입니다
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostDeleteRequestDto {
    @NotNull
    String imgUrl;
}
