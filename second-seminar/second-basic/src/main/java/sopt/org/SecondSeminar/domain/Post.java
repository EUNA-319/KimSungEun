package sopt.org.SecondSeminar.domain;

import lombok.Getter;

@Getter
public class Post {
    private Long id;
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "postId: " + this.id + '\n'+
                ", title= " + this.title + '\n' +
                ", content= " + this.content + '\n';
    }
}
