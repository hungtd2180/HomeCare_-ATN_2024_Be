package com.example.homecare.model.entity;

import com.example.homecare.model.dto.RequestForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @Column(name = "Request_Id", nullable = false, length = 255)
    private String requestId;

    @ManyToOne
    @JoinColumn(name = "Device_Id")
    private Device device;

    @Column(name = "Name", length = 255)
    private String name;

    @Column(name = "Model_Name", length = 255)
    private String modelName;

    @Column(name = "Manufacturer", length = 255)
    private String manufacturer;

    @Column(name = "Type", nullable = false, length = 255)
    private String type;

    @ManyToOne
    @JoinColumn(name = "Employee_Id")
    private Employee employee;

    @Column(name = "Error_Description", nullable = false, length = 255)
    private String errorDescription;

    @Column(name = "Status_Request", nullable = false, length = 255)
    private String statusRequest;

    @Column(name = "Status_Device", nullable = false, length = 255)
    private String statusDevice;

    @Column(name = "Fix_Description", length = 255)
    private String fixDescription;

    @Column(name = "Time_Start")
    private String timeStart;

    @Column(name = "Time_End")
    private String timeEnd;

    @Column(name = "Time_Accept")
    private String timeAccept;
    @Column(name = "Rate")
    private Integer rate;
    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;

    public void fromDto(RequestForm request) {
        setName(request.getName());
        setModelName(request.getModelName());
        setType(request.getType());
        setManufacturer(request.getManufacturer());
        setErrorDescription(request.getErrorDescription());
        setFixDescription(request.getFixDescription());
        setStatusRequest(request.getStatusRequest());
        setStatusDevice(request.getStatusDevice());
        setTimeAccept(request.getTimeAccept());
        setTimeStart(request.getTimeStart());
        setTimeEnd(request.getTimeEnd());
    }
}
