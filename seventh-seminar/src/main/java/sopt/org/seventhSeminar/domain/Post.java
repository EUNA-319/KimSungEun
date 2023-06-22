package sopt.org.seventhSeminar.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 본래 AllArgsconstructor 어노테이션이 있었는데 이는 현재 도메인에서 필요하지 않은 어노테이션이다
public class Post extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

    @Column
    private String thumbnail;

    @Column(nullable=false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isPublic;

    public Post(User user, String thumbnail, String title, String content, Boolean isPublic) {
        this.user = user;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
    }

    public static Post newInstance(User user, String thumbnail, String title, String content, Boolean isPublic){
        return new Post(user, thumbnail, title, content, isPublic);
    }
}
