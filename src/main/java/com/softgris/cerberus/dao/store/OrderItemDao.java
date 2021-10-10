package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.pojo.OrderItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OrderItemDao {

    int saveOrderItem(OrderItemPojo orderItem);

    Optional<OrderItemPojo> getOrderItem(BigInteger orderId, BigInteger itemId);

    List<OrderItemPojo> getAllOrderItems();

    List<OrderItemPojo> getOrderItemsForOrder(BigInteger orderId);

    int deleteOrderItem(BigInteger orderId, BigInteger itemId);

    int updateItem(OrderItemPojo orderItem);
}
