package sopt.org.secondadvanced.controller.User.Dto;

import lombok.Getter;

@Getter
public class GetResponseDto { // 비밀번호는 가져오지 않음
    private String user_id;
    private String email;
    private String name;
    private String nickname;
    private String phone_number;
    private String address;

    public void printinfo(){
        System.out.println(user_id);
    }

    public GetResponseDto(GetResponseDtoBuilder builder){
        this.user_id = builder.user_id;
        this.email = builder.email;
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.phone_number = builder.phone_number;
        this.address = builder.address;
    }

    public static class GetResponseDtoBuilder{
        private String user_id; // required
        private String email; // required
        private String name; // required
        private String nickname; // required
        private String phone_number; // required
        private String address; // optional

        public GetResponseDtoBuilder(String user_id, String email, String name, String nickname, String phone_number){
            this.user_id = user_id;
            this.email = email;
            this.name = name;
            this.nickname = nickname;
            this.phone_number = phone_number;
        }

        public GetResponseDtoBuilder setAddress(String address){
            this.address = address;
            return this;
        }

        public GetResponseDto build(){
            return new GetResponseDto(this);
        }
    }
}
