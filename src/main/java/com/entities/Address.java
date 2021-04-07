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

    @JsonIgnore
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private Customer customer;

}
