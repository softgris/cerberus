package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.UserPojo;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int saveUser(UserPojo user);
    Optional<UserPojo> getUser(Integer i);
    List<UserPojo> getAllUsers();
    int deleteUser(Integer user);
}
