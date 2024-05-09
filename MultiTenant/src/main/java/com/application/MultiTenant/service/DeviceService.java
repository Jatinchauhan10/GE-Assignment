package com.application.MultiTenant.service;

import com.application.MultiTenant.model.Device;
import com.application.MultiTenant.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public void processFile(BufferedReader reader, String tenantId) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            Device device = parseLineToDevice(line, tenantId);
            deviceRepository.save(device);
        }
    }
    public void processFile(String tenantId, InputStream fileStream) {
        Scanner scanner = new Scanner(fileStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Assuming line format matches the device data structure
            Device device = parseLineToDevice(tenantId, line);
            deviceRepository.save(device);
        }
        scanner.close();
    }

    private Device parseLineToDevice(String line, String tenantId) {
        String[] parts = line.split(",");
        Device device = new Device();
        device.setTenantId(tenantId);
        device.setDeviceId(parts[1]);
        device.setModel(parts[2]);
        device.setManufacturer(parts[3]);
        device.setDeviceType(parts[4]);
        device.setApprovalDate(parts[5]);
        return device;
    }
}
