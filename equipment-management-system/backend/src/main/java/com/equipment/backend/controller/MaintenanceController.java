package com.equipment.backend.controller;

import com.equipment.backend.model.MaintenanceLog;
import com.equipment.backend.service.MaintenanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // ✅ POST /api/maintenance
    @PostMapping("/maintenance")
    public MaintenanceLog addMaintenance(@RequestBody MaintenanceLog maintenanceLog) {
        return maintenanceService.addMaintenance(maintenanceLog);
    }

    // ✅ GET /api/equipment/{id}/maintenance
    @GetMapping("/equipment/{id}/maintenance")
    public List<MaintenanceLog> getMaintenanceByEquipment(@PathVariable Long id) {
        return maintenanceService.getMaintenanceByEquipment(id);
    }
}