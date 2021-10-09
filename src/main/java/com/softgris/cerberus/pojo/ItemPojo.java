package com.softgris.cerberus.pojo;

import java.math.BigInteger;

public class ItemPojo {

    private BigInteger itemId;
    private String name;
    private String description;
    private String type;
    private BigInteger priceMinor;
    private BigInteger stock;
    private boolean enabled;

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getPriceMinor() {
        return priceMinor;
    }

    public void setPriceMinor(BigInteger priceMinor) {
        this.priceMinor = priceMinor;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
