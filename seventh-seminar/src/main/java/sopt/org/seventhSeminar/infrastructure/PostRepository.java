package sopt.org.seventhSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.seventhSeminar.domain.Post;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);
}
