package com.app.restaurant.dto;

import com.app.restaurant.data.*;
import lombok.*;

import java.time.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Category category;
    private String description;
    private Type type;
    private String picture;
    private LocalDate date;
    private List<Ingredient> ingredients;
}
