package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.AddressPojo;
import com.softgris.cerberus.pojo.UserPojo;
import com.softgris.cerberus.service.AddressService;
import com.softgris.cerberus.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}")
    public Optional<UserPojo> getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserPojo> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("{id}")
    public int deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
}
