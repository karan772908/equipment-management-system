package com.equipment.backend.service;

import com.equipment.backend.model.Equipment;
import com.equipment.backend.model.MaintenanceLog;
import com.equipment.backend.repository.EquipmentRepository;
import com.equipment.backend.repository.MaintenanceLogRepository;
import com.equipment.backend.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final EquipmentRepository equipmentRepository;

    public MaintenanceService(MaintenanceLogRepository maintenanceLogRepository,
                              EquipmentRepository equipmentRepository) {
        this.maintenanceLogRepository = maintenanceLogRepository;
        this.equipmentRepository = equipmentRepository;
    }

    // ✅ Add maintenance log
    public MaintenanceLog addMaintenance(MaintenanceLog maintenanceLog) {

        // Get equipment
        Equipment equipment = equipmentRepository.findById(
                maintenanceLog.getEquipment().getId()
        ).orElseThrow(() -> new BusinessException("Equipment not found"));

        // 🔥 Business Rule #1
        equipment.setStatus("Active");
        equipment.setLastCleanedDate(maintenanceLog.getMaintenanceDate());

        // Save updated equipment
        equipmentRepository.save(equipment);

        // Set managed equipment object
        maintenanceLog.setEquipment(equipment);

        return maintenanceLogRepository.save(maintenanceLog);
    }

    // ✅ Get maintenance logs by equipment ID
    public List<MaintenanceLog> getMaintenanceByEquipment(Long equipmentId) {

        if (!equipmentRepository.existsById(equipmentId)) {
            throw new BusinessException("Equipment not found");
        }

        return maintenanceLogRepository.findByEquipmentId(equipmentId);
    }
}