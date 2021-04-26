package com.entities;


import com.detailsrequestmodels.CustomerDetailsRequestModel;
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

    public Customer() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CustomersPaidTypes",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "paidtype_id"))
    private Collection<PaidType> paidTypes;

    public void addCustomer(CustomerDetailsRequestModel customerDRM) {
        this.setFirstName(customerDRM.getFirstName());
        this.setLastName(customerDRM.getLastName());
        this.setEmail(customerDRM.getEmail());
        this.setPassword(customerDRM.getPassword());
        this.setPhoneNumber(customerDRM.getPhoneNumber());
    }

    public void changeCustomer(CustomerDetailsRequestModel customerDRM) {
        this.setFirstName(customerDRM.getFirstName());
        this.setLastName(customerDRM.getLastName());
        //this.setEmail(customerDRM.getEmail());
        this.setPassword(customerDRM.getPassword());
        //this.setPhoneNumber(customerDRM.getPhoneNumber());
    }
}
