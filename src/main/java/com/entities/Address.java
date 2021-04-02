package com.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String state;

    @OneToOne(optional = false, mappedBy = "address")
    private Customer customer;

}
