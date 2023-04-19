package sopt.org.secondadvanced.controller.User.Dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto { // 보통 회원 가입 시 userId는 변경이 불가하다
    private Long id;
    private String user_pw;
    private String email;
    private String name;
    private String nickname;
    private String phone_number;
    private String address;
}
