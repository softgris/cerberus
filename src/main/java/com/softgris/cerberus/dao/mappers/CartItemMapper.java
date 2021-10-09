package com.softgris.cerberus.dao.mappers;


import com.softgris.cerberus.pojo.CartItemPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CartItemMapper implements RowMapper<CartItemPojo> {

    @Override
    public CartItemPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartItemPojo cartItem = new CartItemPojo();

        cartItem.setUserId(rs.getBigDecimal("user_id").toBigInteger());
        cartItem.setItemId(rs.getBigDecimal("item_id").toBigInteger());
        cartItem.setQuantity(rs.getBigDecimal("quantity").toBigInteger());

        return cartItem;
    }
}
