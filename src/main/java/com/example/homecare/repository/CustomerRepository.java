package com.example.homecare.repository;

import com.example.homecare.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findCustomersByCollaborator_CollaboratorId(String id);
    List<Customer> findCustomersByCollaborator_CollaboratorIdAndNameContainingAndPhoneContainingAndAddressContaining(String collaboratorId, String name, String phone, String address);
    Optional<Customer> findCustomerByCustomerIdAndCollaborator_CollaboratorId(String customerId, String collaboratorId);
    List<Customer> findCustomersByCollaborator_CollaboratorIdAndTimeAfter(String collaboratorId, String time);
}
