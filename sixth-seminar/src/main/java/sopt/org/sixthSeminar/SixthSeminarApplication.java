package sopt.org.sixthSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Entity 객체가 생성되거나 변경이 되었을 때 자동으로 값 등록
@SpringBootApplication
public class SixthSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SixthSeminarApplication.class, args);
	}

}
