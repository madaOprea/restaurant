package com.app.restaurant.data;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@javax.persistence.Table(name="Reservations")
@NoArgsConstructor
public class Reservation {

    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

    @Column(name="table_id")
    private long tableId;

    @Column(name="client_id")
    private long clientId;

    @Column(name="res_date")
    private Date reservationDate;
}