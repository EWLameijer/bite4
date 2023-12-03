package com.ericwubbo.bite;

import com.ericwubbo.bite.basket.Basket;
import com.ericwubbo.bite.basket.BasketRepository;
import com.ericwubbo.bite.basketitem.BasketItem;
import com.ericwubbo.bite.basketitem.BasketItemRepository;
import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.item.ItemRepository;
import com.ericwubbo.bite.tag.Tag;
import com.ericwubbo.bite.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final ItemRepository itemRepository;

    private final BasketRepository basketRepository;

    private final BasketItemRepository basketItemRepository;

    private final TagRepository tagRepository;

    @Override
    public void run(String... args) {
        if (itemRepository.count() == 0) {
            seedTags();
            seedItems();
            seedBasket();
            System.out.println("Seeded database!");
        } else System.out.println("Database was already seeded...");
    }

    private void seedTags() {
        long count = tagRepository.count();
        if (count == 0) {
            System.out.println("Seeding tags");
            tagRepository.saveAll(List.of(new Tag("fruit"), new Tag("biological")));
            count = tagRepository.count();
        }
        System.out.println(count + " tags in the database.");
    }

    private void seedBasket() {
        long count = basketRepository.count();
        if (count == 0) {
            System.out.println("Seeding basket");
            Item apples = itemRepository.findByName("apples").orElseThrow();
            Item pears = itemRepository.findByName("prunes").orElseThrow();
            var basket = new Basket();
            basketRepository.save(basket);
            basketItemRepository.saveAll(List.of(new BasketItem(apples, basket, 1), new BasketItem(pears, basket, 2)));
            count = basketRepository.count();
        }
        System.out.println(count + " baskets in the database.");
    }

    private void seedItems() {
        long count = itemRepository.count();
        if (count == 0) {
            System.out.println("Seeding items");
            var fruitTag = tagRepository.findByName("fruit").orElseThrow();
            var biologicalTag = tagRepository.findByName("biological").orElseThrow();
            itemRepository.saveAll(List.of(
                    new Item("apples", "2.25", fruitTag, biologicalTag),
                    new Item("bananas", "3.79", fruitTag),
                    new Item("pears", "4.50", fruitTag),
                    new Item("prunes", "1.20", fruitTag, biologicalTag)));
            count = itemRepository.count();
        }
        System.out.println(count + " items in the database.");
    }
}