package com.softgris.cerberus.service;

import com.softgris.cerberus.pojo.ItemPojo;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    int saveItem(ItemPojo item);
    Optional<ItemPojo> getItem(Integer id);
    List<ItemPojo> getAllItems();
    int deleteItem(Integer id);
}
