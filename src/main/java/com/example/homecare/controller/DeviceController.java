package com.example.homecare.controller;

import com.example.homecare.model.dto.DeviceDto;
import com.example.homecare.model.entity.Device;
import com.example.homecare.service.DeviceService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.DEVICE)
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        if (device.isPresent()) {
            return new ResponseEntity<>(device.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<DeviceDto>> getDevicesByUser(@PathVariable String id) {
        List<DeviceDto> devices = deviceService.getDevicesByUserId(id);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody DeviceDto device) {
        try {
            Device savedDevice = deviceService.saveDevice(device);
            return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody Device device) {
        Optional<Device> existingDevice = deviceService.getDeviceById(id);
        if (existingDevice.isPresent()) {
            device.setDeviceId(id);
            Device updatedDevice = deviceService.editDevice(device);
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDevice(@PathVariable String id) {
        try {
            deviceService.deleteDevice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}