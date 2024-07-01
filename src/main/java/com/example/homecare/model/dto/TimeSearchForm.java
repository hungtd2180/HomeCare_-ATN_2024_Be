package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor@AllArgsConstructor
public class TimeSearchForm {
    private Long timeStart;
    private Long timeEnd;
//    public void defaultData(){
//        if (timeStart == null){
//            timeStart = null;
//        }
//        if (timeEnd == null){
//            timeEnd = null;
//        }
//    }
}
