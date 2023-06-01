package sopt.org.sixthSeminar.config.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sopt.org.sixthSeminar.config.jwt.JwtService;

import javax.servlet.http.HttpServletRequest;

// 유저 인증 어노테이션의 동작이 필요하므로 아래와 같이 작성
@RequiredArgsConstructor
@Component
// HandlerMethodArgumentResolver: 컨트롤러 메서드에서 특정 조거에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스
public class UserIdResolver implements HandlerMethodArgumentResolver {
    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class)&&Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader("Authorization");

        // 토큰 검증
        if(!jwtService.verifyToken(token)) {
            throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(token);
        try{
            return Long.parseLong(tokenContents);
        }catch(NumberFormatException e){
            throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)",parameter.getClass(),parameter.getMethod()));
        }
    }
}
