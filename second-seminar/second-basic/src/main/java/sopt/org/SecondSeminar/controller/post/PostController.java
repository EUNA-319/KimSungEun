package sopt.org.SecondSeminar.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.post.dto.request.WriteRequestDto;
import sopt.org.SecondSeminar.service.post.PostService;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postList;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public String write(@RequestBody final WriteRequestDto request){

        // 서비스 계층에 유저를 등록하는 메서드를 호출
        Long postId = postService.write(request);
        System.out.println(postList.get(postId.intValue()-1).toString());
        return "게시글이 등록됐습니다";
    }

    @GetMapping("/post/{postId}")
    public String getOne(@PathVariable final Long postId){
        System.out.println("요청 게시글 아이디: "  + postId);
        // 서비스 계층에서 게시글 아이디로 게시글을 찾는 메서드 호출
        System.out.println(postService.getOne(postId).toString());
        return "게시글 아아디로 조회 성공";
    }

    @GetMapping("/post/search")
    public String search(@RequestParam final String title){
        System.out.println("게시글 제목 검색 인자: " + title);
        //서비스 계층에서 게시글 제목으로 게시글을 찾는 메서드 호출
        System.out.println(postService.search(title).toString());
        return "게시글 제목으로 조회 성공";
    }
}
