package com.services;

import com.entities.Address;
import com.entities.Customer;
import com.repository.AddressRepo;
import com.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepo customerRepo;

    private final AddressRepo addressRepo;

    public Customer findCustomerById(Integer customerId) {
        Customer customerTmp = customerRepo.findById(customerId).orElse(null);
        //if (customerTmp == null) log.error(new IllegalArgumentException("Customer not found"));
        return customerTmp;
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public Iterable<Customer> findAllCustomers()
    {
        return customerRepo.findAll();
    }

    public void deleteCustomer(Customer customer)
    {
        Address address = customer.getAddress();
        customerRepo.delete(customer);
    }
}
