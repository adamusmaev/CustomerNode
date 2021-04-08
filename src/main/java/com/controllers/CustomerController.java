package com.controllers;


import com.detailsrequestmodel.CustomerDetailsRequestModel;
import com.entities.Address;
import com.entities.PaidType;
import com.services.AddressService;
import com.services.CustomerService;
import com.entities.Customer;
import com.services.PaidTypeService;
import com.transfers.CustomerTransfer;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class CustomerController {

    public final CustomerService customerService;
    private final AddressService addressService;
    private final PaidTypeService paidTypeService;

    public CustomerController(CustomerService customerService, AddressService addressService, PaidTypeService paidTypeService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.paidTypeService = paidTypeService;
    }

    @GetMapping(value = "/findCustomer")
    @ResponseBody
    public CustomerTransfer findCustomer(@RequestParam Integer personId) {
        Customer customer = customerService.findCustomerById(personId);
        CustomerTransfer customerTransfer = new CustomerTransfer(customer);
        return customerTransfer;
    }

    @GetMapping("/findAllCustomer")
    @ResponseBody
    public List<CustomerTransfer> findAll() {
        List<CustomerTransfer> customerTransfers = new ArrayList<>();
        for (Customer c : customerService.findAllCustomers()) {
            CustomerTransfer customerTransfer = new CustomerTransfer(c);
            customerTransfers.add(customerTransfer);
        }
        return customerTransfers;
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = new Customer();
        customer.changeCustomer(customerDRM);
        customerService.saveCustomer(customer);
        log.info("Add " + customer.toString());
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

    @PutMapping("/changeCustomer")
    public void changeCustomer(@RequestParam Integer customerId, @RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = customerService.findCustomerById(customerId);
        customer.changeCustomer(customerDRM);
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestParam Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        Address address = customer.getAddress();
        if (address != null) addressService.deleteAddress(address);
        else customerService.deleteCustomer(customer);
    }

    @PostMapping("/addPaidTypeForCustomer")
    public void addPaidTypeForCustomer(@RequestParam Integer customerId, @RequestParam Integer paidTypeId) {
        Customer customer = customerService.findCustomerById(customerId);
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        customer.getPaidTypes().add(paidType);
        paidType.getCustomers().add(customer);
        customerService.saveCustomer(customer);
        paidTypeService.savePaidType(paidType);
    }

}
