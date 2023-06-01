package sopt.org.sixthSeminar.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sopt.org.sixthSeminar.exception.model.UnauthorizedException;
import sopt.org.sixthSeminar.exception.Error;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}") // application.yaml에 정의한 jwt.secert을 값으로 합니다
    private String jwtSecret;

    @PostConstruct // 처음에 딱 한 번 실행하게 됩니다 JWT는 JSON 형태를 Base64로 인코딩 된 상태로 문자열로 표현됩니다
    protected void init(){
        jwtSecret = Base64.getEncoder()
                .encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 발급 -> String 타입
    // Interface로 만들고 구현체로 만들 수도 있습니다 -> 지금은 확장성은 생각하지 않고 바로 구현했습니다
    public String issuedToken(String userId){
        final Date now = new Date();

        // 클레임 생성 => payload 부분에 들어갑니다
        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 120*60*1000L));

        //private claim 등록 => 서버만의 고유 값
        claims.put("userId", userId);

        return Jwts.builder() // 빌더 패턴 사용
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey(){ // application.yaml에서 byte 값을 받아와서 반환을한다
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);

    }
    public boolean verifyToken(String token){
        try{
            final Claims claims = getBody(token); // token의 바디를 반환
            return true;

        }catch(RuntimeException e ){ // 문제 발생 시 runtimeException으로 잡는다
            if(e instanceof ExpiredJwtException){
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage()); // custom으로 만든 에러이다
            }
            return false;
        }
    }

    private Claims getBody(final String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 내용 확인 => getBody => 아까 put해주었으니 get으로 가져온다
    public String getJwtContents(String token){
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }

}
