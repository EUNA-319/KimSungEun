package sopt32.org.thirdadvanced.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User writer;

    @Column(nullable = false)
    private String content;

    @Builder
    public Post(String title, User writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
