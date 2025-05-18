package com.afser.security.controller;

import com.afser.security.model.User;
import com.afser.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @PostMapping("/user")
    public User createuser(@RequestBody User user ){
        return userService.create(user);
    }

    @PostMapping("/login")
    public String verifyuser(@RequestBody User user){
        return userService.verify(user);
    }
}
