package com.example.homecare.repository;

import com.example.homecare.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    List<Employee> findEmployeesByCollaborator_CollaboratorIdOrderByStatusDesc(String collaboratorId);
    Optional<Employee> findEmployeesByCollaborator_CollaboratorIdAndPhone(String collaboratorId, String phone);
    Optional<Employee> findEmployeesByPhoneAndCollaborator_CollaboratorId(String phone, String collaboratorId);
}
