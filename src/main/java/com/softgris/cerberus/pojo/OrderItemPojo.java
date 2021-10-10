package com.softgris.cerberus.pojo;

import java.math.BigInteger;

public class OrderItemPojo {

    BigInteger orderId;
    BigInteger itemId;
    BigInteger priceMinor;
    BigInteger quantity;

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public BigInteger getPriceMinor() {
        return priceMinor;
    }

    public void setPriceMinor(BigInteger priceMinor) {
        this.priceMinor = priceMinor;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}
