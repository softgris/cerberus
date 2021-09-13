package com.softgris.cerberus.dto;

import com.softgris.cerberus.pojo.AddressPojo;

public class AddressDto {

    private final String country;
    private final String county;
    private final String postalCode;
    private final String line1;
    private final String line2;

    public AddressDto(
        String country,
        String county,
        String postalCode,
        String line1,
        String line2) {

        this.country = country;
        this.county = county;
        this.postalCode = postalCode;
        this.line1 = line1;
        this.line2 = line2;
    }

    public AddressDto(AddressPojo address) {
        this.country = address.getCountry();
        this.county = address.getCounty();
        this.postalCode = address.getPostalCode();
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }
}
