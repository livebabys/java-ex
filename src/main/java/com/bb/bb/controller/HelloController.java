package com.bb.bb.controller;


import com.bb.bb.common.Result;
import com.bb.bb.entity.User;
import com.bb.bb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @Autowired
    private UserService userService;



    @GetMapping(value = "/hello")
    public Result userinfo(@RequestParam("id") int id){

        if(id <= 0 ){
            return new Result(999,"用户id不正确");
        }
        User user = new User();
        user.setId(id);

        User userinfo = userService.find(user);
        System.out.println(userinfo);

        return new Result<>(0,"success",userinfo);
        //return  user;
    }
    @PostMapping(value="/register")
    public Result register(@RequestParam("username") String username,
                        @RequestParam("nickname") String nickname,
                        @RequestParam("password") String password){

        User user = new User();
        user.setUsername(username);

        User userObj = userService.find(user);
        if(userObj != null){
            return new Result(999,"用户名已经存在");
        }

        user.setNickname(nickname);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userService.create(user);

        return new Result(0,"success");
    }

    @PostMapping(value="/modify_user_info")
    public Result modifyUserInfo(User user){

//        User user = new User();
//        user.setId(1);
//        user.setNickname("test_nick_name");

        userService.update(user);
        return new Result(0,"success");
    }
}
