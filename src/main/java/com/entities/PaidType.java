package com.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class PaidType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "paidTypes")
    private Collection<Customer> customers;


}
