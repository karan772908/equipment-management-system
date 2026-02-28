package com.equipment.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private LocalDate lastCleanedDate;

    // 🔥 Relationship with EquipmentType
    @ManyToOne
    @JoinColumn(name = "equipment_type_id")
    private EquipmentType equipmentType;

    // 🔥 Relationship with MaintenanceLog
    @JsonManagedReference
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<MaintenanceLog> maintenanceLogs;

    // Constructors
    public Equipment() {}

    public Equipment(String name, String status, LocalDate lastCleanedDate, EquipmentType equipmentType) {
        this.name = name;
        this.status = status;
        this.lastCleanedDate = lastCleanedDate;
        this.equipmentType = equipmentType;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getLastCleanedDate() { return lastCleanedDate; }
    public void setLastCleanedDate(LocalDate lastCleanedDate) {
        this.lastCleanedDate = lastCleanedDate;
    }

    public EquipmentType getEquipmentType() { return equipmentType; }
    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public List<MaintenanceLog> getMaintenanceLogs() { return maintenanceLogs; }
    public void setMaintenanceLogs(List<MaintenanceLog> maintenanceLogs) {
        this.maintenanceLogs = maintenanceLogs;
    }
}