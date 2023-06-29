package sopt.org.eighthSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EighthSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EighthSeminarApplication.class, args);
	}

}
