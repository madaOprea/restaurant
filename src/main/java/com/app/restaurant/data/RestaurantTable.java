package com.app.restaurant.data;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@javax.persistence.Table(name="restaurant_tables")
@NoArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="table_id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="table_no")
    private String tableNumber;

    @Column(name="chair_info")
    private String chairInfo;
}

