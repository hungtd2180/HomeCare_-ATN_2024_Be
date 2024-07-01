package com.example.homecare.controller;

import com.example.homecare.model.dto.EmployeeDto;
import com.example.homecare.model.entity.Employee;
import com.example.homecare.model.entity.Request;
import com.example.homecare.service.EmployeeService;
import com.example.homecare.service.RequestService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.EMPLOYEE)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/collaborator/{id}")
    public ResponseEntity<List<Employee>> getEmployeeByCollaboratorId(@PathVariable String id) {
        List<Employee> employees = employeeService.getEmployeeByCollaboratorId(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/check/{phone}/{id}")
    public ResponseEntity<Employee> testEmployeePhone(@PathVariable String phone, @PathVariable String id) {
        Optional<Employee> employee = employeeService.check(phone, id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("status/{id}")
    public ResponseEntity<Employee> updateStatusEmployee(@PathVariable String id){
        List<Request>  requests = requestService.getRequestByEmployeeAndStatus(id, "2");
        Employee employee = employeeService.getEmployeeById(id).get();
        if (!employee.getStatus().equals("0")){
            if (requests.isEmpty()) {
                employee.setStatus("2");
            } else {
                employee.setStatus("1");
            }
        }
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employee) {
        try {
            Employee savedEmployee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/{phone}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @PathVariable String phone, @RequestBody EmployeeDto employee) {
        Optional<Employee> existingEmployee = employeeService.check(phone, id);
        if (existingEmployee.isPresent()) {
            existingEmployee.get().setName(employee.getName());
            Employee updatedEmployee = employeeService.saveEmployee(existingEmployee.get());
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            employee.get().setStatus("0");
            employeeService.saveEmployee(employee.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
