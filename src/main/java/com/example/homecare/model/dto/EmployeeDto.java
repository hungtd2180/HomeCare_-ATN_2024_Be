package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class EmployeeDto {
    private String employeeId;
    private String name;
    private String phone;
    private String status;
    private String collaboratorId;
}
