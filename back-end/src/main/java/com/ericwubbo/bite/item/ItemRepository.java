package com.ericwubbo.bite.item;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
    
    @Override
    @Query("select e from #{#entityName} e where e.hasBeenDeleted=false")
    @Nonnull
    List<Item> findAll();
}
