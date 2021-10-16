package com.softgris.cerberus.dao.store;

import com.softgris.cerberus.pojo.ItemPojo;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ItemDao {
    int saveItem(ItemPojo item);

    Optional<ItemPojo> getItem(BigInteger itemId);

    List<ItemPojo> getAllItems();

    int deleteItem(BigInteger itemId);

    int updateItem(ItemPojo item);
}
