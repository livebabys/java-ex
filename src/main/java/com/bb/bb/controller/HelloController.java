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
    public Result<User> userinfo(@RequestParam("id") int id){

        User user = new User();
        user.setId(id);

        User userinfo = userService.find(user);
        System.out.println(userinfo);

        return Result<>(0 , "success", user);
        //return  user;
    }
    @PostMapping(value="/register")
    public int register(@RequestParam("username") String username,
                        @RequestParam("nickname") String nickname,
                        @RequestParam("password") String password){

        User user = new User();
        user.setUsername(username);

        User userObj = userService.find(user);
        if(userObj != null){
            return 0;
        }

        user.setNickname(nickname);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userService.create(user);

        return user.getId();
    }

    @PostMapping(value="/modify_user_info")
    public void modifyUserInfo(User user){

//        User user = new User();
//        user.setId(1);
//        user.setNickname("test_nick_name");

        userService.update(user);
    }
}
