package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.UserDao;
import com.softgris.cerberus.pojo.UserPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int saveUser(UserPojo user) {
        return userDao.saveUser(user);
    }

    public Optional<UserPojo> getUser(BigInteger userId) {
        return userDao.getUser(userId);
    }

    @Override
    public List<UserPojo> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int deleteUser(BigInteger userId) {
        return userDao.deleteUser(userId);
    }
}
