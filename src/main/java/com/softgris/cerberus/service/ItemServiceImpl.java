package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.ItemDao;
import com.softgris.cerberus.pojo.ItemPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public int saveItem(ItemPojo item) {
        return itemDao.saveItem(item);
    }

    @Override
    public Optional<ItemPojo> getItem(Integer id) {
        return itemDao.getItem(id);
    }

    @Override
    public List<ItemPojo> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public int deleteItem(Integer id) {
        return itemDao.deleteItem(id);
    }
}
