package com.repo;

import com.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Integer> {

    Address findAddressById(Integer id);
}
