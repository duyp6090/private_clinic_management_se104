/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 *
 * @author iset1enloc
 */
@Entity
@Table(name = "tbl_drug_unit")
public class Drug_Unit {
    @EmbeddedId
    private Drug_UnitId id;  // Composite Key

    @ManyToOne
    @MapsId("drug_id")   // Maps the "role" part of the embedded ID to the Role entity
    @JoinColumn(name = "drug_id")
    private Drugs drug;

    @ManyToOne
    @MapsId("unit_id")  // Maps the "permission" part of the embedded ID to the Permission entity
    @JoinColumn(name = "unit_id")
    private DrugUnits unit;
    

    public Drug_Unit(Drug_UnitId id, Drugs drug, DrugUnits unit) {
        this.id = id;
        this.drug = drug;
        this.unit = unit;
    }

    public Drug_UnitId getId() {
        return id;
    }

    public void setId(Drug_UnitId id) {
        this.id = id;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drug) {
        this.drug = drug;
    }

    public DrugUnits getUnit() {
        return unit;
    }

    public void setUnit(DrugUnits unit) {
        this.unit = unit;
    }   

    
}
