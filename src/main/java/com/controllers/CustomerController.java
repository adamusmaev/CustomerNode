package com.controllers;


import com.entities.Address;
import com.services.AddressService;
import com.services.CustomerService;
import com.entities.Customer;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class CustomerController {

    public final CustomerService customerService;
    private final AddressService addressService;

    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @GetMapping(value = "/findCustomer")
    public Customer findCustomer(@RequestParam Integer personId) {
        Customer customer = customerService.findCustomerById(personId);
        return customer;
    }

    @GetMapping("/findAllCustomer")
    public Iterable<Customer> findAll() {
        return customerService.findAllCustomers();
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String phoneNumber) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhoneNumber(phoneNumber);
        customerService.saveCustomer(customer);
    }

    @PostMapping("/addAddressOfCustomer")
    public void addAddressOfCustomer(@RequestParam Integer customerId,
                                     @RequestParam Integer addressId) {
        Address address = addressService.findAddress(addressId);
        Customer customer = customerService.findCustomerById(customerId);
        customer.setAddress(address);
        address.setCustomer(customer);
        addressService.saveAddress(address);
        customerService.saveCustomer(customer);
    }

}
