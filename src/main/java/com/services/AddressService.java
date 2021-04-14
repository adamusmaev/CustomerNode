package com.services;

import com.entities.Address;
import com.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {


    private final AddressRepository addressRepository;

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public Address findAddress(Integer id) {
        return addressRepository.findAddressById(id);
    }

    public Iterable<Address> findAllAddresses() {
        return addressRepository.findAll();
    }
    public void deleteAddress(Address address)
    {
        addressRepository.delete(address);
    }
}
