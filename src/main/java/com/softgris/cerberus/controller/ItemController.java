package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.ItemPojo;
import com.softgris.cerberus.service.ItemService;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public int saveItem(@RequestBody ItemPojo item) {
        return itemService.saveItem(item);
    }

    @GetMapping("{itemId}")
    public Optional<ItemPojo> getItem(@PathVariable("itemId") BigInteger itemId) {
        return itemService.getItem(itemId);
    }

    @GetMapping
    public List<ItemPojo> getAllItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("{itemId}")
    public int deleteItem(@PathVariable("itemId") BigInteger itemId) {
        return itemService.deleteItem(itemId);
    }
}
