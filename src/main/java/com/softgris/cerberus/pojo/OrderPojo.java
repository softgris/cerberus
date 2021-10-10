package com.softgris.cerberus.pojo;

import java.math.BigInteger;
import java.time.Instant;

public class OrderPojo {

    BigInteger orderId;
    BigInteger userId;
    Instant dateOrdered;
    boolean cancelled;

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Instant getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Instant dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
