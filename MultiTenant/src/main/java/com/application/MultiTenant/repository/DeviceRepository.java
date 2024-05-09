package com.application.MultiTenant.repository;

import com.application.MultiTenant.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByTenantIdAndDeviceId(String tenantId, String deviceId);
}

