package com.app.restaurant.data;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TableReservation {

    private long tableId;

    private long clientId;

    private String tableName;

    private String tableNumber;

    private String firstName;

    private String lastName;

    private Date date;
}
