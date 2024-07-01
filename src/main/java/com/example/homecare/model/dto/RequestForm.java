package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class RequestForm {
    private String requestId;
    private String deviceId;
    private String name;
    private String modelName;
    private String type;
    private String manufacturer;
    private String errorDescription;
    private String fixDescription;
    private String statusDevice;
    private String statusRequest;
    private String timeStart;
    private String timeEnd;
    private String timeAccept;
    private String userId;
    private String collaboratorId;
}
