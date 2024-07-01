package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class PriceObject {
    private String priceId;
    private String type;
    private String name;
    private String priceLow;
    private String priceHigh;
    private String collaboratorId;
}
