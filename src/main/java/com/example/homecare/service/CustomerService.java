package com.example.homecare.service;

import com.example.homecare.model.dto.CustomerObject;
import com.example.homecare.model.dto.CustomerSearchForm;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Customer;
import com.example.homecare.model.entity.User;
import com.example.homecare.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.DateUtil.getCurrentTime;
import static com.example.homecare.util.DateUtil.getFirstDayOfMonth;
import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CollaboratorService collaboratorService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }
    public List<Customer> getCustomerList(String collaboratorId) {
        return customerRepository.findCustomersByCollaborator_CollaboratorId(collaboratorId);
    }
    public List<Customer> findCustomer(CustomerSearchForm searchForm){
        return customerRepository.findCustomersByCollaborator_CollaboratorIdAndNameContainingAndPhoneContainingAndAddressContaining
                (searchForm.getCollaboratorId(), searchForm.getName(), searchForm.getPhone(), searchForm.getAddress());
    }
    public Customer saveCustomer(CustomerObject object) {
        Optional<Customer> customer = customerRepository.findCustomerByCustomerIdAndCollaborator_CollaboratorId(object.getCustomerId(), object.getCollaboratorId());
        if (customer.isEmpty()) {

            User user = userService.getUserById(object.getCustomerId()).get();
            Collaborator collaborator = collaboratorService.getCollaboratorById(object.getCollaboratorId()).get();
            Customer newCustomer = new Customer(createId("customer"), user.getUserId(), user.getAddress(), user.getPhone(), user.getName(), user.getEmail(), getCurrentTime(), collaborator);
            return customerRepository.save(newCustomer);
        } else {
            return customerRepository.save(customer.get());
        }

    }
    public List<Customer> getCustomerFromTime(String collaboratorId){
        return customerRepository.findCustomersByCollaborator_CollaboratorIdAndTimeAfter(collaboratorId, getFirstDayOfMonth());
    }
    public Customer editCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
