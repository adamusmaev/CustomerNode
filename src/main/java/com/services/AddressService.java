package com.services;

import com.entities.Address;
import com.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    public AddressRepo addressRepo;

    public void saveAddress(Address address)
    {
        addressRepo.save(address);
    }

    public Address findAddress(Integer id)
    {
        return addressRepo.findAddressById(id);
    }
}
