package sopt.org.secondadvanced.domain;

public class User {
    private Long id; // 데이터베이스 사용하지 않지만, primary_key라고 생각하고 구현
    private String user_id; // required
    private String user_pw; // required
    private String email; // required
    private String name; // required
    private String nickname; // required
    private String phone_number; // required
    private String address; // optional

    public Long getId(){return id;}

    public String getUser_id() {
        return user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id){ // DB 연동이 아닌 List에 관리하므로 여기서는 setId로 회원 번호를 부여
        this.id = id;
    }

    private User(UserBuilder builder){
        this.user_id = builder.user_id;
        this.user_pw = builder.user_pw;
        this.email = builder.email;
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.phone_number = builder.phone_number;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    //Builder Class
    public static class UserBuilder{
        private String user_id; // required
        private String user_pw; // required
        private String email; // required
        private String name; // required
        private String nickname; // optional
        private String phone_number; // required
        private String address; // optional

        public UserBuilder(String user_id, String user_pw, String email, String name, String nickname, String phone_number){
            this.user_id = user_id;
            this.user_pw = user_pw;
            this.email = email;
            this.name = name;
            this.nickname = nickname;
            this.phone_number = phone_number;
        }

        public UserBuilder setAddress(String address){
            this.address = address;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
