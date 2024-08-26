package com.baar.spring_security_jwt.controller;


import com.baar.spring_security_jwt.model.User;
import com.baar.spring_security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}