package com.equipment.backend.controller;

import com.equipment.backend.model.Equipment;
import com.equipment.backend.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    // ✅ GET all equipment
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    // ✅ GET by ID
    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    // ✅ POST create equipment
    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentService.createEquipment(equipment);
    }

    // ✅ PUT update equipment
    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id,
                                     @RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment);
    }

    // ✅ DELETE equipment
    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}