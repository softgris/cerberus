package com.softgris.cerberus.model;

import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "\"user\"") // user is reserved keyword in PostgreSQL
public class User {

  @Id
  @Column(name = "user_id")
  BigInteger id;

  @NotNull
  @Column(name = "address_id")
  BigInteger addressId;

  @NotNull
  @Column(name = "email")
  String email;

  @NotNull
  @Column(name = "password_hash")
  String passwordHash;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;

    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return id.mod(BigInteger.valueOf(Integer.MAX_VALUE)).intValue();
  }
}
