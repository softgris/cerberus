package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.UserPojo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    int saveUser(UserPojo user);
    Optional<UserPojo> getUser(Integer id);
    List<UserPojo> getAllUsers();
    int deleteUser(Integer id);
}
