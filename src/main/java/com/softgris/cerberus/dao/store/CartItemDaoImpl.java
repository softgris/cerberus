package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.dao.mappers.CartItemMapper;
import com.softgris.cerberus.pojo.CartItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CartItemDaoImpl implements CartItemDao {

    RowMapper<CartItemPojo> cartItemMapper;
    JdbcTemplate jdbcTemplate;

    public CartItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.cartItemMapper = new CartItemMapper();
    }

    @Override
    public int saveCartItem(CartItemPojo cartItem) {
        String query = "INSERT INTO cart_item(user_id, item_id, quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query,
            cartItem.getUserId(),
            cartItem.getItemId(),
            cartItem.getQuantity());
    }

    @Override
    public Optional<CartItemPojo> getCartItem(BigInteger userId, BigInteger itemId) {
        String query = "SELECT * FROM cart_item WHERE user_id = ? AND item_id = ?";
        List<CartItemPojo> result = jdbcTemplate.query(query, cartItemMapper, userId, itemId);
        return result.stream().findFirst();
    }

    @Override
    public List<CartItemPojo> getAllCartItems() {
        String query = "SELECT * FROM cart_item";
        return jdbcTemplate.query(query, cartItemMapper);
    }

    @Override
    public List<CartItemPojo> getCartItemsForUser(BigInteger userId) {
        String query = "SELECT * FROM cart_item WHERE user_id = ?";
        return jdbcTemplate.query(query, cartItemMapper, userId);
    }

    @Override
    public int deleteCartItem(BigInteger userId, BigInteger itemId) {
        String query = "DELETE FROM cart_item WHERE user_id = ? AND item_id = ?";
        return jdbcTemplate.update(query, userId, itemId);
    }

    @Override
    public int updateItem(CartItemPojo cartItem) {
        String query = "UPDATE cart_item(quantity) SET (?) WHERE userId = ? AND itemId = ?";
        return jdbcTemplate.update(query, cartItem.getUserId(), cartItem.getItemId());
    }
}
