package sopt.org.seventhSeminar.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import sopt.org.seventhSeminar.domain.RefreshToken;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RedisRepository {

    private RedisTemplate redisTemplate;

    public RedisRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RefreshToken refreshToken) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getUserId(), Duration.ofDays(30)); // 30일 뒤 메모리에서 삭제된다
    }

    public Optional<RefreshToken> findById(final String refreshToken) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        Long userId = valueOperations.get(refreshToken);

        if (Objects.isNull(userId)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, userId)); // 저장되어있던 RefreshToken 객체 반환
    }

    public void delete(final String refreshToken){
        redisTemplate.delete(refreshToken);
    }
}
