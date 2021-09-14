package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.UserDao;
import com.softgris.cerberus.pojo.UserPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<UserPojo> getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public List<UserPojo> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }
}
