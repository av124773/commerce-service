package com.gtelant.commerce_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.models.Item;
import com.gtelant.commerce_service.repositories.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllItemsByOrderReference(String orderReference) {
        return itemRepository.findAllByOrderReference(orderReference);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> saveAllItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }
}
