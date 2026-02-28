package com.equipment.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate maintenanceDate;

    private String description;

    // 🔥 Many maintenance logs belong to one equipment
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    // Constructors
    public MaintenanceLog() {}

    public MaintenanceLog(LocalDate maintenanceDate, String description, Equipment equipment) {
        this.maintenanceDate = maintenanceDate;
        this.description = description;
        this.equipment = equipment;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public LocalDate getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public Equipment getEquipment() { return equipment; }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}