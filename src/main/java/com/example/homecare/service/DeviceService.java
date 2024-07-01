package com.example.homecare.service;

import com.example.homecare.model.dto.DeviceDto;
import com.example.homecare.model.entity.Device;
import com.example.homecare.model.entity.User;
import com.example.homecare.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserService userService;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    public List<DeviceDto> getDevicesByUserId(String userId) {return deviceRepository.findDevicesByUserUserId(userId).
            stream().map(DeviceDto::fromEntity).collect(Collectors.toList());}

    public Optional<Device> getDeviceById(String deviceId) {
        return deviceRepository.findById(deviceId);
    }

    public Device saveDevice(DeviceDto form) {
        Device device = new Device();
        if (form.getDeviceId() == null || form.getDeviceId().isEmpty()) {
            form.setDeviceId(createId("device"));
        }
        if (form.getUserId() != null) {
            Optional<User> user = userService.getUserById((form.getUserId()));
            device.setUser(user.get());
        }
        form.defaultData();
        device.fromDto(form);
        return deviceRepository.save(device);
    }
    public Device editDevice(Device device) {
        return deviceRepository.save(device);
    }
    public void deleteDevice(String deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
