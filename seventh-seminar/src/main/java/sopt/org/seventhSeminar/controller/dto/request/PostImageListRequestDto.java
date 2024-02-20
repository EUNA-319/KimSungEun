package sopt.org.seventhSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter // ModelAttribute는 리스트 자동 바인딩을 못해줍니다 그래서 setter를 사용했습니다!
@Schema(description = "복수 사진을 포함하는 게시글 생성 요청 DTO")
public class PostImageListRequestDto {
    @Schema(description = "사진들을 담는 리스트")
    private List<MultipartFile> postImages;

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
