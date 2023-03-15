package com.example.seconttimespring.webrest;

import com.example.seconttimespring.entity.Item;
import com.example.seconttimespring.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("api")
public class ItemResource implements Serializable {
    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("items")
    public ResponseEntity create(Item item){
        Item item1 = itemService.save(item);
        return ResponseEntity.ok(item1);
    }
}
