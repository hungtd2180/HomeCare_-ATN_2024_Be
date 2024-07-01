package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class RequestEditForm {
    private String employeeId;
    private String fixDescription;
    private String statusDevice;
}
