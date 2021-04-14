package com.controllers;

import com.detailsrequestmodels.CustomerDetailsRequestModel;
import com.entities.Address;
import com.entities.Customer;
import com.entities.PaidType;
import com.services.AddressService;
import com.services.CustomerService;
import com.services.PaidTypeService;
import com.transfers.CustomerTransfer;
import com.transfers.PaidTypeTransfer;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/{personId}")
    @ResponseBody
    public CustomerTransfer findCustomer(@PathVariable Integer personId) {///-----
        Customer customer = customerService.findCustomerById(personId);
        return new CustomerTransfer(customer);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<CustomerTransfer> findAll() {
        List<CustomerTransfer> customerTransfers = new ArrayList<>();
        for (Customer c : customerService.findAllCustomers()) {
            CustomerTransfer customerTransfer = new CustomerTransfer(c);
            customerTransfers.add(customerTransfer);
        }
        return customerTransfers;
    }

    @PostMapping("/addition")
    public void addCustomer(@RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = new Customer();
        customer.changeCustomer(customerDRM);
        customerService.saveCustomer(customer);
        log.info("Add " + customer.toString());
    }

    @PostMapping("/{customerId}/addition/address/{addressId}")
    public void addAddressOfCustomer(@PathVariable Integer customerId,
                                     @PathVariable Integer addressId) {
        Address address = addressService.findAddress(addressId);
        Customer customer = customerService.findCustomerById(customerId);
        customer.setAddress(address);
        address.setCustomer(customer);
        addressService.saveAddress(address);
        customerService.saveCustomer(customer);
    }

    @PutMapping("/{customerId}/renewal")
    public void changeCustomer(@PathVariable Integer customerId, @RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = customerService.findCustomerById(customerId);
        customer.changeCustomer(customerDRM);
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{customerId}/deletion")
    public void deleteCustomer(@PathVariable Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        Address address = customer.getAddress();
        if (address != null) addressService.deleteAddress(address);
        else customerService.deleteCustomer(customer);
    }

    @PostMapping("/{customerId}/addition/paidtype/{paidTypeId}")
    public void addPaidTypeForCustomer(@PathVariable Integer customerId, @PathVariable Integer paidTypeId) {
        Customer customer = customerService.findCustomerById(customerId);
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        customer.getPaidTypes().add(paidType);
        paidType.getCustomers().add(customer);
        customerService.saveCustomer(customer);
        paidTypeService.savePaidType(paidType);
    }

    @GetMapping(value = "/paidtypes")
    public List<PaidTypeTransfer> findPaidTypes(@RequestParam Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        Iterable<PaidType> paidTypes = customer.getPaidTypes();
        List<PaidTypeTransfer> res = new ArrayList<>();
        for (PaidType p : paidTypes) {
            res.add(new PaidTypeTransfer(p));
        }
        return res;
    }
}
