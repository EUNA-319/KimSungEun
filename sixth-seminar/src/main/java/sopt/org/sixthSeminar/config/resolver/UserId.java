package sopt.org.sixthSeminar.config.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 유저 인증이 필요한 모든 API에서 헤더에 존재하는 토큰을 가져와 토큰을 검증하고
// payload에서 필요한 데이터를 꺼내온 후 해당 유저 관련 정보로 유저를 인증하는 것은 비효율적이다
// 이런 일을 줄이기 위해 유저 인증을 하는 어노테이션을 작성한다
@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치를 지정하는 어노테이션
// 컨트롤러에서 사용하는 파라미터에 유저 인증 어노테이션을 붙여줄 것이다
@Retention(RetentionPolicy.RUNTIME) // 어노테이션이 언제까지 유효할지 정하는 어노테이션
// 프로그램이 실행되는 동안에는 계속 유효해야 하기 때문에 RUNTIME으로 설정
public @interface UserId {
}
