package com.controllers;

import com.detailsrequestmodels.CustomerDetailsRequestModel;
import com.entities.Address;
import com.entities.Customer;
import com.entities.PaidType;
import com.services.AddressService;
import com.services.CustomerService;
import com.services.PaidTypeService;
import com.tokens.Token;
import com.transfers.AddressTransfer;
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
    public CustomerTransfer findCustomer(@PathVariable Integer personId) {
        Customer customer = customerService.findCustomerById(personId);
        CustomerTransfer customerTransfer = new CustomerTransfer();
        customerTransfer.setId(customer.getId());
        customerTransfer.setEmail(customer.getEmail());
        customerTransfer.setFirstName(customer.getFirstName());
        customerTransfer.setLastName(customer.getLastName());
        customerTransfer.setPassword(customer.getPassword());
        customerTransfer.setPhoneNumber(customer.getPhoneNumber());
        if (customer.getAddress() != null) customerTransfer.setAddressTransfer(new AddressTransfer(customer.getAddress()));
        List<PaidTypeTransfer> paidTypeTransferList = new ArrayList<>();
        for (PaidType p : customer.getPaidTypes()){
            PaidTypeTransfer paidTypeTransfer = new PaidTypeTransfer(p);
            paidTypeTransferList.add(paidTypeTransfer);
        }
        customerTransfer.setPaidTypeTransfers(paidTypeTransferList);
        return customerTransfer;
    }

    @GetMapping
    @ResponseBody
    public List<CustomerTransfer> findAll() {
        List<CustomerTransfer> customerTransfers = new ArrayList<>();
        for (Customer c : customerService.findAllCustomers()) {
            CustomerTransfer customerTransfer = new CustomerTransfer();
            customerTransfer.setId(c.getId());
            customerTransfer.setEmail(c.getEmail());
            customerTransfer.setFirstName(c.getFirstName());
            customerTransfer.setLastName(c.getLastName());
            customerTransfer.setPassword(c.getPassword());
            customerTransfer.setPhoneNumber(c.getPhoneNumber());
            customerTransfers.add(customerTransfer);
            if (c.getAddress() != null) customerTransfer.setAddressTransfer(new AddressTransfer(c.getAddress()));
            List<PaidTypeTransfer> paidTypeTransferList = new ArrayList<>();
            for (PaidType p : c.getPaidTypes()){
                PaidTypeTransfer paidTypeTransfer = new PaidTypeTransfer(p);
                paidTypeTransferList.add(paidTypeTransfer);
            }
            customerTransfer.setPaidTypeTransfers(paidTypeTransferList);
        }
        return customerTransfers;
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = new Customer();
        customer.addCustomer(customerDRM);
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

    @PutMapping("/{customerId}")
    public void changeCustomer(@PathVariable Integer customerId, @RequestBody CustomerDetailsRequestModel customerDRM) {
        Customer customer = customerService.findCustomerById(customerId);
        customer.changeCustomer(customerDRM);
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
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

    @GetMapping(value = "/{customerId}/paidtypes")
    public List<PaidTypeTransfer> findPaidTypes(@PathVariable Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        Iterable<PaidType> paidTypes = customer.getPaidTypes();
        List<PaidTypeTransfer> res = new ArrayList<>();
        for (PaidType p : paidTypes) {
            res.add(new PaidTypeTransfer(p));
        }
        return res;
    }

    @GetMapping(value = "/{customerId}/token")
    public String getToken(@PathVariable Integer customerId){
        Customer customer = customerService.findCustomerById(customerId);
        return Token.getToken(customer.getEmail(), customer.getPassword());
    }

    @GetMapping("/id")
    public Integer getIdCustomer(@RequestParam String token){
        if (!Token.checkToken(token)) throw new IllegalArgumentException();
        log.info(Token.getEmail(token));
        Customer customer = customerService.findCustomerByEmailAndPassword(Token.getEmail(token),Token.getPassword(token));
        return customer.getId();
    }
}
