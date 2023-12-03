package com.ericwubbo.bite.basket;

import com.ericwubbo.bite.basketitem.BasketItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Basket {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    // without mappedBy, creates a separate join table
    // IF basketItems is present, need @OneToMany
    // new HashSet if I create a Basket myself (based on, for example, a DTO)
    private final Set<BasketItem> basketItems = new HashSet<>();

    private final LocalDateTime dateTime = LocalDateTime.now();;

    public void addBasketItem(BasketItem basketItem) {
        basketItems.add(basketItem);
    }
}
