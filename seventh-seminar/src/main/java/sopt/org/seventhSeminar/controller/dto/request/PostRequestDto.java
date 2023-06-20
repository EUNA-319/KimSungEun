package sopt.org.seventhSeminar.controller.dto.request;

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
public class PostRequestDto {
    @NotNull
    private MultipartFile thumbnail;

    @NotNull
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
