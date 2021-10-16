package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.OrderPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<OrderPojo> {

    @Override
    public OrderPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderPojo order = new OrderPojo();
        order.setOrderId(rs.getBigDecimal("order_id").toBigInteger());
        order.setUserId(rs.getBigDecimal("user_id").toBigInteger());
        order.setDateOrdered(rs.getTimestamp("date_ordered").toInstant());
        order.setCancelled(rs.getBoolean("cancelled"));

        return order;
    }
}
