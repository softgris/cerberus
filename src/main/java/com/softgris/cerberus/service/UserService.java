package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.UserPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserService {

    int saveUser(UserPojo user);

    Optional<UserPojo> getUser(BigInteger userId);

    List<UserPojo> getAllUsers();

    int deleteUser(BigInteger userId);
}
