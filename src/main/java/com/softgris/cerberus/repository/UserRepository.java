package com.softgris.cerberus.repository;

import com.softgris.cerberus.model.User;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, BigInteger> {

}
