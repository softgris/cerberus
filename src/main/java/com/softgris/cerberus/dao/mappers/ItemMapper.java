package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.ItemPojo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemMapper implements RowMapper<ItemPojo> {

    @Override
    public ItemPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemPojo item = new ItemPojo();
        item.setItemId(rs.getBigDecimal("item_id").toBigInteger());
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setType(rs.getString("type"));
        item.setPriceMinor(rs.getBigDecimal("price_minor").toBigInteger());
        item.setStock(rs.getBigDecimal("stock").toBigInteger());
        item.setEnabled(rs.getBoolean("enabled"));

        return item;
    }
}
