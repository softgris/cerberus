package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.OrderItemPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderItemMapper implements RowMapper<OrderItemPojo> {

    @Override
    public OrderItemPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemPojo orderItem = new OrderItemPojo();
        orderItem.setOrderId(rs.getBigDecimal("order_item").toBigInteger());
        orderItem.setItemId(rs.getBigDecimal("item_id").toBigInteger());
        orderItem.setPriceMinor(rs.getBigDecimal("price_minor").toBigInteger());
        orderItem.setQuantity(rs.getBigDecimal("quantity").toBigInteger());

        return orderItem;
    }
}
