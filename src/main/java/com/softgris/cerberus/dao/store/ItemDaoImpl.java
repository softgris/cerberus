package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.dao.mappers.ItemMapper;
import com.softgris.cerberus.pojo.ItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {

    RowMapper<ItemPojo> itemMapper;
    JdbcTemplate jdbcTemplate;

    public ItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        itemMapper = new ItemMapper();
    }

    @Override
    public int saveItem(ItemPojo item) {
        String query = "INSERT INTO item(item_id, name, description, type, price_minor, stock, enabled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
            item.getItemId(),
            item.getName(),
            item.getDescription(),
            item.getType(),
            item.getPriceMinor(),
            item.getStock(),
            item.isEnabled());
    }

    @Override
    public Optional<ItemPojo> getItem(BigInteger itemId) {
        String query = "SELECT * FROM item WHERE item_id = ?";
        List<ItemPojo> result = jdbcTemplate.query(query, itemMapper, itemId);
        return result.stream().findFirst();
    }

    @Override
    public List<ItemPojo> getAllItems() {
        String query = "SELECT * FROM item";
        return jdbcTemplate.query(query, itemMapper);
    }

    @Override
    public int deleteItem(BigInteger itemId) {
        String query = "DELETE FROM item WHERE item_id = ?";
        return jdbcTemplate.update(query, itemId);
    }

    @Override
    public int updateItem(ItemPojo item) {
        String query = "UPDATE item(name, description, type, price_minor, stock, enabled) SET (?, ?, ?, ?, ?, ?) WHERE item_id = ?";
        return jdbcTemplate.update(query,
            item.getName(),
            item.getDescription(),
            item.getType(),
            item.getPriceMinor(),
            item.getStock(),
            item.isEnabled(),
            item.getItemId());
    }
}
