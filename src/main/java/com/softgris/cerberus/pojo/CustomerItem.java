package com.softgris.cerberus.pojo;

import java.math.BigInteger;

public class CustomerItem {

    private BigInteger customerItemId;
    private BigInteger customerId;
    private BigInteger itemId;

    public BigInteger getCustomerItemId() {
        return customerItemId;
    }

    public void setCustomerItemId(BigInteger customerItemId) {
        this.customerItemId = customerItemId;
    }

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }
}
