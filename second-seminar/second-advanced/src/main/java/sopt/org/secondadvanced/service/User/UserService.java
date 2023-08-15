package sopt.org.secondadvanced.service.User;

import org.springframework.stereotype.Service;
import sopt.org.secondadvanced.controller.User.Dto.GetResponseDto;
import sopt.org.secondadvanced.controller.User.Dto.RegisterRequestDto;
import sopt.org.secondadvanced.controller.User.Dto.UpdateRequestDto;
import sopt.org.secondadvanced.domain.User;

import java.util.Optional;

import static sopt.org.secondadvanced.SecondAdvancedApplication.*;

@Service
public class UserService {
    public Optional<GetResponseDto> getByUserId(String userId){
        GetResponseDto find = null;
        for (User user : userList) {
            if (user.getUser_id().equals(userId)) {
                find = new GetResponseDto.GetResponseDtoBuilder(user.getUser_id(), user.getEmail(), user.getName(),user.getNickname(),user.getPhone_number())
                        .setAddress(user.getNickname())
                        .build();
                break;
            }
        }
        return Optional.of(find);
    }

    public Optional<GetResponseDto> getByNickname(String nickname){
        GetResponseDto find = null;
        for (User user : userList) {
            if (user.getNickname().equals(nickname)) {
                find = new GetResponseDto.GetResponseDtoBuilder(user.getUser_id(), user.getEmail(), user.getName(),user.getNickname(), user.getPhone_number())
                        .setAddress(user.getNickname())
                        .build();
                break;
            }
        }
        return Optional.of(find);
    }

    public GetResponseDto register(RegisterRequestDto request){
        boolean address;
        User newUser;
        GetResponseDto newUserInfo;
        if(request.getAddress().isEmpty())
            address = false;
        else
            address = true;

        if(address){
            newUser = new User.UserBuilder(request.getUser_id(), request.getUser_pw(), request.getEmail(), request.getName(), request.getNickname(), request.getPhone_number())
                    .setAddress(request.getAddress())
                    .build();
            newUserInfo = new GetResponseDto.GetResponseDtoBuilder(newUser.getUser_id(), newUser.getEmail(), newUser.getName(), request.getNickname(), newUser.getPhone_number())
                    .setAddress(newUser.getAddress())
                    .build();
        }
        else{
            newUser = new User.UserBuilder(request.getUser_id(), request.getUser_pw(), request.getEmail(), request.getName(), request.getNickname(), request.getPhone_number())
                    .build();
            newUserInfo = new GetResponseDto.GetResponseDtoBuilder(newUser.getUser_id(), newUser.getEmail(), newUser.getName(), request.getNickname(), newUser.getPhone_number())
                    .build();
        }

        userList.add(newUser);
        newUser.setId(++primary_key);
        return newUserInfo;
    }

    public Optional<GetResponseDto> update(UpdateRequestDto request){
        boolean address;
        User updateUser=null;
        int i=0;
        GetResponseDto updateUserInfo = null;

        for (i=0;i<userList.size();i++) {
            User user = userList.get(i);
            if (user.getId().equals(request.getId())) {
                if(request.getAddress().isEmpty())
                    address = false;
                else
                    address = true;
                if(address){
                    updateUser = new User.UserBuilder(user.getUser_id(), request.getUser_pw(), request.getEmail(), request.getName(), request.getNickname(), request.getPhone_number())
                            .setAddress(request.getAddress())
                            .build();
                    updateUser.setId(user.getId());
                    updateUserInfo = new GetResponseDto.GetResponseDtoBuilder(user.getUser_id(), user.getEmail(), user.getName(), request.getNickname(), user.getPhone_number())
                            .setAddress(user.getAddress())
                            .build();
                }
                else{
                    updateUser = new User.UserBuilder(user.getUser_id(), request.getUser_pw(), request.getEmail(), request.getName(), request.getNickname(), request.getPhone_number())
                            .build();
                    updateUser.setId(user.getId());
                    updateUserInfo = new GetResponseDto.GetResponseDtoBuilder(user.getUser_id(), user.getEmail(), user.getName(), request.getNickname(), user.getPhone_number())
                            .build();
                }
                break;
            }
        }
        userList.set(i,updateUser);
        return Optional.of(updateUserInfo);
    }

    public Optional<GetResponseDto> delete(Long identifyId){
        GetResponseDto deleteUser = null;
        User deluser=null;
        for (User user : userList) {
            if (user.getId().equals(identifyId)) {
                deleteUser = new GetResponseDto.GetResponseDtoBuilder(user.getUser_id(), user.getEmail(), user.getName(), user.getNickname(), user.getPhone_number())
                        .setAddress(user.getNickname())
                        .build();
                deluser = user; // userList에서 찾은 user remove
                break;
            }
        }
        userList.remove(deluser);
        return Optional.of(deleteUser);
    }


}
