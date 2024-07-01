package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class RequestStatisticDto {
    private String statusRequest;
    private Integer count;
}
