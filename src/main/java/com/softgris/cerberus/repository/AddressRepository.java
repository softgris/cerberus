package com.softgris.cerberus.repository;

import com.softgris.cerberus.model.Address;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, BigInteger> {

}
