package com.services;

import com.entities.Address;
import com.entities.Customer;
import com.repository.AddressRepository;
import com.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    public Customer findCustomerById(Integer customerId) {
        Customer customerTmp = customerRepository.findById(customerId).orElse(null);
        //if (customerTmp == null) log.error(new IllegalArgumentException("Customer not found"));
        return customerTmp;
    }

    public Customer findCustomerByEmailAndPassword(String email, String password){
        return customerRepository.findCustomerByEmailAndPassword(email, password);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Iterable<Customer> findAllCustomers()
    {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Customer customer)
    {
        Address address = customer.getAddress();
        customerRepository.delete(customer);
    }
}
