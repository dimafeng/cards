package com.dimafeng.cards.controller;

import com.dimafeng.cards.model.User;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/signup")
    @ResponseBody
    public User signup(@RequestBody Credentials credentials) {
        return userService.createUser(credentials.email, credentials.password);
    }

    public static class Credentials {
        public String email;
        public String password;
    }
}
