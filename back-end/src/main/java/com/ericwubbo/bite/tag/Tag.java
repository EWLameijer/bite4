package com.ericwubbo.bite.tag;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
