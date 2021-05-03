package com.repository;

import com.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerByEmailAndPassword(String email, String phone);
}
