package sopt.org.secondadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.secondadvanced.controller.User.Dto.RegisterRequestDto;
import sopt.org.secondadvanced.controller.User.Dto.UpdateRequestDto;
import sopt.org.secondadvanced.service.User.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/search/")
    public String getById(@RequestParam final String userId){
        userService.getByUserId(userId);
        return "userId로 회원찾기";
    }

    @GetMapping("/user/search/nickName/")
    public String search(@RequestParam final String nickname){
        userService.getByNickname(nickname);
        return "nickname으로 회원 찾기";
    }

    @PostMapping("/user")
    public String register(@RequestBody final RegisterRequestDto request){
        userService.register(request);
        return "회원가입하기";
    }

    @PutMapping("/user/update")
    public String register(@RequestBody final UpdateRequestDto request){
        userService.update(request);
        return "회원정보 수정 완료";
    }

    @DeleteMapping("/user/delete/{identifyId}")
    public String delete(@PathVariable final Long identifyId){
        userService.delete(identifyId);
        return "회원탈퇴하기";
    }

}

