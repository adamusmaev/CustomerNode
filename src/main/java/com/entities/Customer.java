package com.entities;


import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @NaturalId
    private String email;

    private String password;

    @NaturalId
    private String phoneNumber;

    @OneToOne(optional = true, mappedBy = "customer", fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CustomersPaidTypes",
                joinColumns = @JoinColumn(name = "customer_id"),
                inverseJoinColumns = @JoinColumn(name = "paidtype_id"))
    private Collection<PaidType> paidTypes;
}
