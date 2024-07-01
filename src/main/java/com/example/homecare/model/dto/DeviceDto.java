package com.example.homecare.model.dto;

import com.example.homecare.model.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class DeviceDto {
    private String deviceId;
    private String name;
    private String modelName;
    private String manufacturer;
    private String type;
    private String userId;


    public static DeviceDto fromEntity(Device device){
        return new DeviceDto(device.getDeviceId(), device.getName(), device.getModelName(), device.getManufacturer(), device.getType(), device.getUser().getUserId());
    }
    public void defaultData() {
        if (name == null)
            name = "";
        if (modelName == null)
            modelName = "";
        if (manufacturer == null)
            manufacturer = "";
        if (type == null)
            type = "";
    }
}
