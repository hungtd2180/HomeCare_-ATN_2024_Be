package com.example.homecare.model.entity;

import com.example.homecare.model.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "Employee_Id", nullable = false, length = 255)
    private String employeeId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;

    public void fromDto(EmployeeDto dto){
        setEmployeeId(dto.getEmployeeId());
        setName(dto.getName());
        setPhone(dto.getPhone());
        setStatus(dto.getStatus());
    }
}
