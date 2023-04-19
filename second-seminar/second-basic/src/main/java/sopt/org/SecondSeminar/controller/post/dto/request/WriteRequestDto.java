package sopt.org.SecondSeminar.controller.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteRequestDto {
    private String title;
    private String content;
}
