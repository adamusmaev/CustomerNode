package com.transfers;


import com.entities.Address;
import lombok.Data;

@Data
public class AddressTransfer {

    private String city;
    private String state;
    private String country;

    public AddressTransfer(Address address) {
        this.city = address.getCity();
        this.state = address.getState();
        this.country = address.getCountry();
    }
}
