package com.ericwubbo.bite.item;

import com.ericwubbo.bite.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@ToString
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToMany
    private Set<Tag> tags;

    @Setter
    private boolean hasBeenDeleted = false;

    private Item() {
    }

    public Item(String name, String price, Tag... tags) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.tags = Set.of(tags);
    }

    public Item(long id, String name, String price, Tag... tags) {
        this(name, price, tags);
        this.id = id;
    }
}
