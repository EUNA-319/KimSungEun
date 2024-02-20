package sopt.org.seventhSeminar.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시켜주는 역할
public abstract class AuditingTimeEntity { // 혼자서만은 의미가 없기 때문에 해당 클래스 객체화 시킬 이유가 없으므로 추상클래스로 작성

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

