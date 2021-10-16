package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.UserPojo;
import com.softgris.cerberus.service.UserService;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public int saveUser(@RequestBody UserPojo user) {
        return userService.saveUser(user);
    }

    @GetMapping("{userId}")
    public Optional<UserPojo> getUser(@PathVariable("userId") BigInteger userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public List<UserPojo> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("{userId}")
    public int deleteUser(@PathVariable("userId") BigInteger userId) {
        return userService.deleteUser(userId);
    }
}
