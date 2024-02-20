package sopt.org.seventhSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.seventhSeminar.controller.dto.request.PostImageListRequestDto;
import sopt.org.seventhSeminar.controller.dto.request.PostRequestDto;
import sopt.org.seventhSeminar.domain.Post;
import sopt.org.seventhSeminar.domain.Image;
import sopt.org.seventhSeminar.domain.User;
import sopt.org.seventhSeminar.exception.Error;
import sopt.org.seventhSeminar.exception.model.NotFoundException;
import sopt.org.seventhSeminar.infrastructure.ImageRepository;
import sopt.org.seventhSeminar.infrastructure.PostRepository;
import sopt.org.seventhSeminar.infrastructure.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void create(Long userId, String postThumbnailImageUrl, PostRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Post newPost = Post.newInstance(
                user,
                postThumbnailImageUrl,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        postRepository.save(newPost);
    }

    @Transactional
    public void create(Long userId, List<String> boardImageUrlList, PostImageListRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Post newPost = Post.newInstance(
                user,
                boardImageUrlList.get(0), // 첫번째 사진을 섬네일로 사용
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        // 이미지 생성
        for (String boardImageUrl: boardImageUrlList) {
            imageRepository.save(Image.newInstance(newPost, boardImageUrl));
        }
    }
}
