package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.pojo.OrderPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    int saveOrder(OrderPojo order);

    Optional<OrderPojo> getOrder(BigInteger orderId);

    List<OrderPojo> getAllOrders();

    List<OrderPojo> getOrdersForUser(BigInteger userId);

    int deleteOrder(BigInteger orderId);

    int updateOrder(OrderPojo order);
}
