package com.softgris.cerberus.service;

import com.softgris.cerberus.dao.store.ItemDao;
import com.softgris.cerberus.pojo.ItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
    public Optional<ItemPojo> getItem(BigInteger itemId) {
        return itemDao.getItem(itemId);
    }

    @Override
    public List<ItemPojo> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public int deleteItem(BigInteger itemId) {
        return itemDao.deleteItem(itemId);
    }
}
