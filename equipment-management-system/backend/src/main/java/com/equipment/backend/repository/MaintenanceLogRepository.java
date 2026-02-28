package com.equipment.backend.repository;

import com.equipment.backend.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceLogRepository 
        extends JpaRepository<MaintenanceLog, Long> {

    List<MaintenanceLog> findByEquipmentId(Long equipmentId);
}