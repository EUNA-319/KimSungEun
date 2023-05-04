package sopt32.org.thirdadvanced.infrastructure;

import org.springframework.data.repository.Repository;
import sopt32.org.thirdadvanced.domain.Post;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);
    Post findPostById(Long postId);


}
