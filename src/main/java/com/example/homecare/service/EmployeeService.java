package com.example.homecare.service;

import com.example.homecare.model.dto.EmployeeDto;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Employee;
import com.example.homecare.model.entity.Request;
import com.example.homecare.repository.EmployeeRepository;
import com.example.homecare.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CollaboratorService collaboratorService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }
    public List<Employee> getEmployeeByCollaboratorId(String collaboratorId) {
        return employeeRepository.findEmployeesByCollaborator_CollaboratorIdOrderByStatusDesc(collaboratorId);
    }
    public Optional<Employee> getEmployeeByPhone(String phone, String collaboratorId) {
        return employeeRepository.findEmployeesByCollaborator_CollaboratorIdAndPhone(collaboratorId, phone);
    }
    public Optional<Employee> check(String phone, String collaboratorId){
        return employeeRepository.findEmployeesByPhoneAndCollaborator_CollaboratorId(phone, collaboratorId);
    }
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee createEmployee(EmployeeDto dto) {
        Employee employee = new Employee();
        if (dto.getEmployeeId() == null || dto.getEmployeeId().isEmpty()) {
            dto.setEmployeeId(createId("employee"));
        }
        if (dto.getCollaboratorId() != null){
            Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(dto.getCollaboratorId());
            employee.setCollaborator(collaborator.get());
        }
        employee.fromDto(dto);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
