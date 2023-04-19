package sopt.org.secondadvanced.controller.User.Dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterRequestDto {
    private String user_id;
    private String user_pw;
    private String email;
    private String name;
    private String nickname;
    private String phone_number;
    private String address;
}
