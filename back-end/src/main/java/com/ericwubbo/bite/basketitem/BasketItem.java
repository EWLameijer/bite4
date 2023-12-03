package com.ericwubbo.bite.basketitem;

import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.basket.Basket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BasketItem {
    @Id // Ideally composite key out of itemId and basketId
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable=false) //@JoinColumn(name="item_id", nullable=false)
    private Item item;

    @ManyToOne // nullable = false required!, nullable default value is true...
    @JoinColumn(nullable=false)
    private Basket basket;

    private int count;

    public BasketItem(Item item, Basket basket, int count) {
        this.item = item;
        this.basket = basket;
        this.count = count;
    }
}
