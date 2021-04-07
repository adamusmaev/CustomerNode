package com.controllers;

import com.entities.Address;
import com.repo.AddressRepo;
import com.services.AddressService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class AddressController {

    public final AddressService addressService;

    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @PostMapping("/addAddress")
    public void addAddress(@RequestParam String city,
                           @RequestParam String country,
                           @RequestParam String state)
    {
        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);
        address.setState(state);
        addressService.saveAddress(address);
    }
}
