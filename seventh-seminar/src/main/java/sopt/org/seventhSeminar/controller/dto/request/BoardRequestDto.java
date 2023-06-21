package sopt.org.seventhSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "사진을 포함하지 않는 게시글 생성 요청 DTO")
public class BoardRequestDto {
    @NotBlank
    @Schema(description = "게시글 제목")
    private String title;

    @NotBlank
    @Schema(description = "게시글 내용")
    private String content;

    @NotNull
    @Schema(description = "게시글 발행 여부")
    private Boolean isPublic;
}
