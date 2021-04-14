package com.controllers;

import com.detailsrequestmodels.AddressDetailsRequestModel;
import com.entities.Address;
import com.services.AddressService;
import com.transfers.AddressTransfer;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class AddressController {

    public final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addition")
    public void addAddress(@RequestBody AddressDetailsRequestModel addressDRM) {
        Address address = new Address();
        address.setCity(addressDRM.getCity());
        address.setCountry(addressDRM.getCountry());
        address.setState(addressDRM.getState());
        addressService.saveAddress(address);
        log.info("Add " + address.toString());
    }

    @GetMapping("/all")
    @ResponseBody
    public List<AddressTransfer> findAllAddresses() {
        List<AddressTransfer> addressTransfers = new ArrayList<>();
        for (Address a : addressService.findAllAddresses()) {
            AddressTransfer addressTransfer = new AddressTransfer(a);
            addressTransfers.add(addressTransfer);
        }
        return addressTransfers;
    }
}
