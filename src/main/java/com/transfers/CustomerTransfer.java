package com.transfers;

import com.entities.Customer;
import com.entities.PaidType;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Log4j
public class CustomerTransfer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private AddressTransfer addressTransfer;
    private List<PaidTypeTransfer> paidTypeTransfers = new ArrayList<>();

    public CustomerTransfer(Customer customer) {

        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phoneNumber = customer.getPhoneNumber();
        if (customer.getAddress() == null) log.error("Customer:" + customer.toString() + " doesn't have address");
        else this.addressTransfer = new AddressTransfer(customer.getAddress());
        for (PaidType p : customer.getPaidTypes()) {
            paidTypeTransfers.add(new PaidTypeTransfer(p));
        }
    }
}
