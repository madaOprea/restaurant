package com.app.restaurant.data;

import lombok.Data;

import javax.persistence.*;

@Data
@javax.persistence.Table(name="client")
public class Client {

    @Id
    @Column(name="client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String emailAddress;

    @Column(name="address")
    private String address;

    @Column(name="country")
    private String country;

    @Column(name="state")
    private String state;

    @Column(name="phone_number")
    private String phoneNumber;
}