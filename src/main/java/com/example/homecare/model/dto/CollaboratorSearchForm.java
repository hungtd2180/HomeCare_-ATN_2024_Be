package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor@AllArgsConstructor
public class CollaboratorSearchForm {
    private String address;
    private String name;
    private String field;
    public void defaultData(){
        if (address == null)
            address = "";
        if (name == null)
            name = "";
        if (field == null)
            field = "";
    }
}
