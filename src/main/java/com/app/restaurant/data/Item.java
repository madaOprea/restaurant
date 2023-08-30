package com.app.restaurant.data;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "picture")
    private String picture;

    @Column(name = "uploaded")
    private LocalDate date;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "items_ingredients",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Ingredient> ingredients;
}
