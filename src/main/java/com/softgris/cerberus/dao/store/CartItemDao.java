package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.pojo.CartItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CartItemDao {

    int saveCartItem(CartItemPojo cartItem);

    Optional<CartItemPojo> getCartItem(BigInteger userId, BigInteger itemId);

    List<CartItemPojo> getAllCartItems();

    List<CartItemPojo> getCartItemsForUser(BigInteger userId);

    int deleteCartItem(BigInteger userId, BigInteger itemId);

    int updateItem(CartItemPojo cartItem);
}
