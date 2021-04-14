package com.repository;

import com.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    Address findAddressById(Integer id);
}
