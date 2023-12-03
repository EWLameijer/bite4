package com.ericwubbo.bite.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/items")
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item postItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable("id") long id) {
        itemRepository.deleteById(id);
    }

    record ItemDto(String name, String price) {
    }

    @GetMapping("{id}")
    public Optional<Item> getById(@PathVariable("id") long id) {
        return itemRepository.findById(id);
    }
}
