package com.softgris.cerberus.dao.user;

import com.softgris.cerberus.dao.mappers.UserMapper;
import com.softgris.cerberus.pojo.UserPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final RowMapper<UserPojo> userMapper;
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userMapper = new UserMapper();
    }

    @Override
    public int saveUser(UserPojo user) {

        String query = "INSERT INTO user_t(user_id, email, password, role_id, enabled) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
            user.getUserId(),
            user.getEmail(),
            user.getPassword(),
            user.getRoleId(),
            user.isEnabled());
    }

    public Optional<UserPojo> getUser(BigInteger userId) {
        String query = "SELECT * FROM user_t WHERE user_id = ?";

        List<UserPojo> result = jdbcTemplate.query(query, userMapper, userId);

        return result.stream().findFirst();
    }

    public List<UserPojo> getAllUsers() {
        String query = "SELECT * FROM user_t";
        return jdbcTemplate.query(query, userMapper);
    }

    public int deleteUser(BigInteger userId) {
        String query = "DELETE FROM user_t WHERE user_id = ?";
        return jdbcTemplate.update(query, userId);
    }

    public int updateUser(UserPojo user) {

        String query = "UPDATE user_t(email, password, role_id, enabled) SET (?, ?, ?, ?) WHERE user_id = ?";
        return jdbcTemplate.update(query,
            user.getEmail(),
            user.getPassword(),
            user.getRoleId(),
            user.isEnabled(),
            user.getUserId());
    }
}
