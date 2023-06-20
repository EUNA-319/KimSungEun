package sopt.org.seventhSeminar.controller.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter // ModelAttribute는 리스트 자동 바인딩을 못해줍니다 그래서 setter를 사용했습니다!
public class PostImageListRequestDto {
    private List<MultipartFile> postImages;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
