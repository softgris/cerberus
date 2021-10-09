package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.UserPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    int saveUser(UserPojo user);

    Optional<UserPojo> getUser(BigInteger userId);

    List<UserPojo> getAllUsers();

    int deleteUser(BigInteger userId);

    int updateUser(UserPojo user);
}
