package com.softgris.cerberus.dao.mappers;

import com.softgris.cerberus.pojo.ItemPojo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper {

    @Override
    public ItemPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemPojo item = new ItemPojo();
        item.setItemId(rs.getBigDecimal("item_id").toBigInteger());
        item.setType(rs.getString("type"));
        item.setDescription(rs.getString("description"));
        item.setStock(rs.getInt("stock"));

        return item;
    }
}
