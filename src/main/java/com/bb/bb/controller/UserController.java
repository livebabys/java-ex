package com.bb.bb.controller;

import com.alibaba.fastjson.JSONObject;
import com.bb.bb.common.JwtHelper;
import com.bb.bb.common.Result;
import com.bb.bb.entity.User;
import com.bb.bb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        Map<String,Object> resultData = new HashMap<String, Object>();
        resultData.put("token",jwtHelper.generateToken(claims));

        return new Result(0,"success",resultData);
    }
}
