package com.ericwubbo.bite.basket;

import com.ericwubbo.bite.basketitem.BasketItem;
import com.ericwubbo.bite.basketitem.BasketItemRepository;
import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.item.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketRepository basketRepository;

    private final BasketItemRepository basketItemRepository;

    private final ItemRepository itemRepository;

    @GetMapping
    public Set<Basket> test() {
        return basketRepository.findByDateTimeIsAfter(LocalDateTime.now().minusDays(1));
    }

    record BasketItemDto(long itemId, int count) {
    }

    record BasketDto(BasketItemDto[] basketItems) {
    }

    @PostMapping
    @Transactional
    public Basket post(@RequestBody BasketDto basketDto) {
        var basket = basketRepository.save(new Basket());
        for (BasketItemDto basketItemDto : basketDto.basketItems()) {
            Item item = itemRepository.findById(basketItemDto.itemId).orElseThrow();
            BasketItem basketItem = new BasketItem(item, basket, basketItemDto.count);
            basketItemRepository.save(basketItem);
            basket.addBasketItem(basketItem);
        }
        return basket;
    }

}
