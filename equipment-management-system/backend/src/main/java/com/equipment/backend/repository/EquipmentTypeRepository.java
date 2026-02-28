package com.equipment.backend.repository;

import com.equipment.backend.model.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {

    Optional<EquipmentType> findByName(String name);
}