package sopt.org.seventhSeminar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Post post;

    @Column(nullable = false)
    private String imageUrl;

    private Image(Post post, String imageUrl){
        this.post = post;
        this.imageUrl = imageUrl;
    }

    public static Image newInstance(Post post, String imageUrl){
        return new Image(post, imageUrl);
    }
}
