package com.softgris.cerberus.dao;

import com.softgris.cerberus.dao.mappers.ItemMapper;
import com.softgris.cerberus.pojo.ItemPojo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDaoImpl implements ItemDao {

    RowMapper itemMapper;
    JdbcTemplate jdbcTemplate;

    public ItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        itemMapper = new ItemMapper();
    }

    @Override
    public int saveItem(ItemPojo item) {

        String query = "INSERT INTO item(item_id, type, description, stock) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, item.getItemId(), item.getType(), item.getDescription(), item.getStock());
    }

    @Override
    public Optional<ItemPojo> getItem(Integer id) {
        String query = "SELECT * FROM item WHERE item_id = ?";

        List<ItemPojo> result = jdbcTemplate.query(query, itemMapper, id);

        return result.stream().findFirst();
    }

    @Override
    public List<ItemPojo> getAllItems() {
        String query = "SELECT * FROM item";
        return jdbcTemplate.query(query, itemMapper);
    }

    @Override
    public int deleteItem(Integer id) {
        String query = "DELETE FROM item WHERE item_id = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public int updateItem(ItemPojo item) {
//        String query = "UPDATE customer SET customer_id = " + customer.getAddressId() +
//                ", email = " + customer.getEmail() + ", password_hash = " + customer.getPasswordHash() +
//                " where customer_id = " + customer.getCustomer();
//        return jdbcTemplate.update(query);

        String query = "UPDATE item(type, description, stock) SET (?, ?, ?) WHERE item_id = ?";
        return jdbcTemplate.update(query, item.getType(), item.getDescription(), item.getStock(), item.getItemId());
    }


}
