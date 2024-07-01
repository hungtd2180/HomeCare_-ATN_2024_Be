package com.example.homecare.controller;

import com.example.homecare.model.dto.CustomerObject;
import com.example.homecare.model.dto.CustomerSearchForm;
import com.example.homecare.model.entity.Customer;
import com.example.homecare.service.CustomerService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.CUSTOMER)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("collaborator/{id}")
    public ResponseEntity<List<Customer>> getCollaboratorCustomers(@PathVariable String id) {
    List<Customer> customers = customerService.getCustomerList(id);
    return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("/statistic/{id}")
    public ResponseEntity<List<Customer>> getStatistic(@PathVariable String id) {
        List<Customer> customers = customerService.getCustomerFromTime(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<Customer>> findCustomer(@RequestBody CustomerSearchForm searchForm) {
        searchForm.defaultData();
        List<Customer> customers = customerService.findCustomer(searchForm);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerObject customer) {
        try {
            Customer savedCustomer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            customer.setCustomerId(id);
            Customer updatedCustomer = customerService.editCustomer(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
