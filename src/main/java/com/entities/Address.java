package com.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String country;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private Customer customer;

}
