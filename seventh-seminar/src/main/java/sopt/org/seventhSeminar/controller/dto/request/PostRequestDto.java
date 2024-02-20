package sopt.org.seventhSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "단일 사진을 포함하는 게시글 생성 요청 DTO")
public class PostRequestDto {
    @NotNull
    @Schema(description = "섬네일")
    private MultipartFile thumbnail;

    @NotNull
    @Schema(description = "게시글 제목")
    private String title;

    @NotBlank
    @Schema(description = "게시글 내용")
    private String content;

    @NotNull
    @Schema(description = "게시글 발행 여부")
    private Boolean isPublic;
}
