package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.dao.mappers.OrderItemMapper;
import com.softgris.cerberus.pojo.OrderItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderItemDaoImpl implements OrderItemDao {

    private final RowMapper<OrderItemPojo> orderItemMapper;
    private final JdbcTemplate jdbcTemplate;

    public OrderItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderItemMapper = new OrderItemMapper();
    }

    @Override
    public int saveOrderItem(OrderItemPojo orderItem) {
        String query = "INSERT INTO order_item(order_id, item_id, price_minor, quantity) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query,
            orderItem.getOrderId(),
            orderItem.getItemId(),
            orderItem.getPriceMinor(),
            orderItem.getQuantity());
    }

    @Override
    public Optional<OrderItemPojo> getOrderItem(BigInteger orderId, BigInteger itemId) {
        String query = "SELECT * FROM order_item WHERE order_id = ? AND item_id = ?";
        List<OrderItemPojo> result = jdbcTemplate.query(query, orderItemMapper, orderId, itemId);
        return result.stream().findFirst();
    }

    @Override
    public List<OrderItemPojo> getAllOrderItems() {
        String query = "SELECT * FROM order_item";
        return jdbcTemplate.query(query, orderItemMapper);
    }

    @Override
    public List<OrderItemPojo> getOrderItemsForOrder(BigInteger orderId) {
        String query = "SELECT * FROM order_item WHERE order_id = ?";
        return jdbcTemplate.query(query, orderItemMapper, orderId);
    }

    @Override
    public int deleteOrderItem(BigInteger orderId, BigInteger itemId) {
        String query = "DELETE FROM order_item WHERE order_id = ? AND item_id = ?";
        return jdbcTemplate.update(query, orderId, itemId);
    }

    @Override
    public int updateItem(OrderItemPojo orderItem) {
        String query = "UPDATE order_item(price_minor, quantity) SET (?, ?) WHERE order_id = ? AND item_id = ?";
        return jdbcTemplate.update(query,
            orderItem.getPriceMinor(),
            orderItem.getQuantity(),
            orderItem.getOrderId(),
            orderItem.getItemId());
    }
}
