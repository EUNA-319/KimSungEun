package sopt.org.seventhSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter // ModelAttribute는 리스트를 자동 바인딩을 못해줍니다! 그래서 setter를 써준 것 입니다
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "AWS에 저장된 이미지 삭제 DTO")
public class PostDeleteRequestDto {
    @NotNull
    @Schema(description = "삭제할 이미지 경로")
    String imgUrl;
}
