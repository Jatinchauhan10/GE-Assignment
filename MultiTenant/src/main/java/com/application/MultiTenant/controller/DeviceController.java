package com.application.MultiTenant.controller;

import com.application.MultiTenant.model.Device;
import com.application.MultiTenant.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/{tenantId}/{deviceId}")
    public List<Device> getDeviceData(@PathVariable String tenantId, @PathVariable String deviceId) {
        return deviceRepository.findByTenantIdAndDeviceId(tenantId, deviceId);
    }
}

