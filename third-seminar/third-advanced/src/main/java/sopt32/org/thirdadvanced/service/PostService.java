package sopt32.org.thirdadvanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt32.org.thirdadvanced.controller.dto.request.PostRequestDto;
import sopt32.org.thirdadvanced.controller.dto.response.PostResponseDto;
import sopt32.org.thirdadvanced.domain.Post;
import sopt32.org.thirdadvanced.infrastructure.PostRepository;
import sopt32.org.thirdadvanced.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto save(PostRequestDto request){
        Post post = Post.builder()
                .writer(userRepository.findById(request.getUserId()))
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        postRepository.save(post);
        return PostResponseDto.of(post.getWriter().getNickname(), post.getTitle(),post.getContent());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPostById(Long postId){
        Post post = postRepository.findPostById(postId);
        return PostResponseDto.of(post.getWriter().getNickname(), post.getTitle(), post.getContent());
    }


}
