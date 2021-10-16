package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.dao.mappers.OrderMapper;
import com.softgris.cerberus.pojo.OrderPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderDaoImpl implements OrderDao {

    RowMapper<OrderPojo> orderMapper;
    JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.orderMapper = new OrderMapper();
    }

    @Override
    public int saveOrder(OrderPojo order) {
        String query = "INSERT INTO order_t(order_id, user_id, date_ordered, cancelled) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query,
            order.getOrderId(),
            order.getUserId(),
            order.getDateOrdered(),
            order.isCancelled());
    }

    @Override
    public Optional<OrderPojo> getOrder(BigInteger orderId) {
        String query = "SELECT * FROM order_t WHERE order_id = ?";
        List<OrderPojo> result = jdbcTemplate.query(query, orderMapper, orderId);
        return result.stream().findFirst();
    }

    @Override
    public List<OrderPojo> getAllOrders() {
        String query = "SELECT * FROM order_t";
        return jdbcTemplate.query(query, orderMapper);
    }

    @Override
    public List<OrderPojo> getOrdersForUser(BigInteger userId) {
        String query = "SELECT * FROM order_t WHERE user_id = ?";
        return jdbcTemplate.query(query, orderMapper, userId);
    }

    @Override
    public int deleteOrder(BigInteger orderId) {
        String query = "DELETE FROM order_t WHERE order_id = ?";
        return jdbcTemplate.update(query, orderId);
    }

    @Override
    public int updateOrder(OrderPojo order) {
        String query = "UPDATE order_t(user_id, date_ordered, cancelled) SET (?, ?, ?) WHERE order_id = ?";
        return jdbcTemplate.update(query,
            order.getUserId(),
            order.getDateOrdered(),
            order.isCancelled(),
            order.getOrderId());
    }
}
