package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class CustomerSearchForm {
    private String name;
    private String phone;
    private String address;
    private String collaboratorId;
    public void defaultData(){
        if (name == null)
            name = "";
        if (phone == null)
            phone = "";
        if (address == null)
            address = "";
    }
}
