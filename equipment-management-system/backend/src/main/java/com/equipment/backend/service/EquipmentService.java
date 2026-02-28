package com.equipment.backend.service;

import com.equipment.backend.model.Equipment;
import com.equipment.backend.repository.EquipmentRepository;
import com.equipment.backend.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // ✅ Get all equipment
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // ✅ Create new equipment
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // ✅ Get equipment by ID
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Equipment not found"));
    }

    // ✅ Update equipment
    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {

        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Equipment not found"));

        // 🔥 Business Rule #2 – Status Constraint
        if ("Active".equalsIgnoreCase(updatedEquipment.getStatus())) {

            LocalDate lastCleanedDate =
                    updatedEquipment.getLastCleanedDate() != null
                            ? updatedEquipment.getLastCleanedDate()
                            : equipment.getLastCleanedDate();

            if (lastCleanedDate == null ||
                lastCleanedDate.isBefore(LocalDate.now().minusDays(30))) {

                throw new BusinessException(
                        "Equipment cannot be set to Active. Last cleaned date is older than 30 days."
                );
            }
        }

        // ✅ Apply updates
        equipment.setName(updatedEquipment.getName());
        equipment.setStatus(updatedEquipment.getStatus());
        equipment.setEquipmentType(updatedEquipment.getEquipmentType());
        equipment.setLastCleanedDate(updatedEquipment.getLastCleanedDate());

        return equipmentRepository.save(equipment);
    }

    // ✅ Delete equipment
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new BusinessException("Equipment not found");
        }
        equipmentRepository.deleteById(id);
    }
}