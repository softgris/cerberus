package com.softgris.cerberus.controller;

import com.softgris.cerberus.pojo.AddressPojo;
import com.softgris.cerberus.pojo.ItemPojo;
import com.softgris.cerberus.service.AddressService;
import com.softgris.cerberus.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}")
    public Optional<ItemPojo> getItem(@PathVariable("id") Integer id) {
        return itemService.getItem(id);
    }

    @GetMapping
    public List<ItemPojo> getAllItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("{id}")
    public int deleteItem(@PathVariable("id") Integer id) {
        return itemService.deleteItem(id);
    }
}
