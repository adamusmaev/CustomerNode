package com.services;

import com.entities.Address;
import com.repository.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {


    private final AddressRepo addressRepo;

    public void saveAddress(Address address) {
        addressRepo.save(address);
    }

    public Address findAddress(Integer id) {
        return addressRepo.findAddressById(id);
    }

    public Iterable<Address> findAllAddresses() {
        return addressRepo.findAll();
    }
    public void deleteAddress(Address address)
    {
        addressRepo.delete(address);
    }
}
