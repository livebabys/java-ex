package com.bb.bb.controller;


import com.bb.bb.entity.User;
import com.bb.bb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/hello")
    public String hello(){
        //System.out.println(id);
        int id = 1;
        User userinfo = userService.find(id);
        System.out.println(userinfo);
        return  "hello spring boot";
    }
    @PostMapping(value="/register")
    public String register(){
        return "success";
    }
}
