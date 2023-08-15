package sopt.org.SecondSeminar.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.SecondSeminar.controller.post.dto.request.WriteRequestDto;
import sopt.org.SecondSeminar.domain.Post;

import java.util.Optional;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postList;

@Service
public class PostService {

    public Long write(WriteRequestDto request){
        Post newPost = new Post(request.getTitle(), request.getContent());
        postList.add(newPost);
        newPost.setId((long)postList.size());
        return newPost.getId();
    }

    public Optional<Post> getOne(Long postId){
        return postList.stream()
                .filter(post->post.getId().equals(postId))
                .findAny();
    }

    public <Optional> java.util.Optional<Post> search(String title){
        return postList.stream()
                .filter(user -> user.getTitle().equals(title))
                .findAny();
    }


}
