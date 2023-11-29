package com.ericwubbo.bite.basket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Set<Basket> findByDateTimeIsAfter(LocalDateTime dateTime);
}
