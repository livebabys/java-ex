package com.bb.bb.controller;

import com.alibaba.fastjson.JSONObject;
import com.bb.bb.common.JwtHelper;
import com.bb.bb.common.Result;
import com.bb.bb.entity.User;
import com.bb.bb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password
                        ){
        User userModel = new User();
        userModel.setUsername(username);
        User user = userService.find(userModel);
        if(user == null){
            return  new Result(999,"用户不存在");
        }

        System.out.println(user.getPassword());
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));

        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())) ){
            return new Result(999,"密码不正确");
        }

        System.out.println(JSONObject.toJSONString(user));
        Map<String,Object> claims = new HashMap<String, Object>();
        claims.put("username",user.getUsername());
        claims.put("uid",user.getId());

        Map<String,Object> resultData = new HashMap<String, Object>();
        resultData.put("token",jwtHelper.generateToken(claims));

        return new Result(0,"success",resultData);
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

    @PostMapping(value="/api/auth/modify_user_info")
    public Result modifyUserInfo(User user){

//        User user = new User();
//        user.setId(1);
//        user.setNickname("test_nick_name");

        userService.update(user);
        return new Result(0,"success");
    }
    @GetMapping("/api/auth/userinfo")
    public Result userinfo(HttpServletRequest request){
        HttpSession session =request.getSession();
        int id = (int)session.getAttribute("uid");
        User user = new User();
        user.setId(id);
        User userinfo = userService.find(user);
        return new Result<>(0,"success",userinfo);
    }
}
