package sopt.org.secondadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sopt.org.secondadvanced.domain.User;

import java.util.ArrayList;

@SpringBootApplication
public class SecondAdvancedApplication {
	public static long primary_key=0;
	public static ArrayList<User> userList;
	public static void main(String[] args) {

		SpringApplication.run(SecondAdvancedApplication.class, args);
		userList = new ArrayList<>(); // 회원을 저장할 ArrayList
	}

}
