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
@Table(name = "address")
public class Address {

  @Id
  @Column(name = "address_id")
  BigInteger id;

  @NotNull
  @Column(name = "country")
  String country;

  @Column(name = "county")
  String county;

  @NotNull
  @Column(name = "postal_code")
  String postalCode;

  @NotNull
  @Column(name = "line_1")
  String lineOne;

  @Column(name = "line_2")
  String lineTwo;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Address address = (Address) o;

    return Objects.equals(id, address.id);
  }

  @Override
  public int hashCode() {
    return id.mod(BigInteger.valueOf(Integer.MAX_VALUE)).intValue();
  }
}
