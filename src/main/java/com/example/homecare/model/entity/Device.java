package com.example.homecare.model.entity;

import com.example.homecare.model.dto.DeviceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
public class Device {

    @Id
    @Column(name = "Device_Id", nullable = false, length = 255)
    private String deviceId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Model_Name", length = 255)
    private String modelName;

    @Column(name = "Manufacturer", length = 255)
    private String manufacturer;

    @Column(name = "Type", nullable = false, length = 255)
    private String type;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    public void fromDto(DeviceDto dto){
        setDeviceId(dto.getDeviceId());
        setName(dto.getName());
        setModelName(dto.getModelName());
        setManufacturer(dto.getManufacturer());
        setType(dto.getType());
    }
}
