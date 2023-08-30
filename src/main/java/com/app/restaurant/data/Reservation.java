package com.app.restaurant.data;

import jakarta.annotation.Nullable;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@javax.persistence.Table(name="reservation")
@NoArgsConstructor
public class Reservation {

    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

    @Column(name="table_id")
    private long tableId;

    @Column(name="client_id")
    @Nullable
    private long clientId;

    @Column(name="res_date")
    private Date reservationDate;

    @Column(name="res_name")
    private String reservationName;
}