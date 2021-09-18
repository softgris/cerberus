package com.softgris.cerberus.dao;

import com.softgris.cerberus.pojo.ItemPojo;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    int saveItem(ItemPojo item);

    Optional<ItemPojo> getItem(Integer id);

    List<ItemPojo> getAllItems();

    int deleteItem(Integer id);

    int updateItem(ItemPojo item);
}
