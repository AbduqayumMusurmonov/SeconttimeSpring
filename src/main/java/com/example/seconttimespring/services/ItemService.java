package com.example.seconttimespring.services;

import com.example.seconttimespring.entity.Item;
import com.example.seconttimespring.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item){
       return itemRepository.save(item);
    }
}
