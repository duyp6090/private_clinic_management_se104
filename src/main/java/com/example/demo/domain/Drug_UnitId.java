package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Drug_UnitId implements Serializable {

    private Integer drug_id;
    private Integer unit_id;

    // Default constructor
    public Drug_UnitId() {}

    // Parameterized constructor
    public Drug_UnitId(Integer drug_id, Integer unit_id) {
        this.drug_id = drug_id;
        this.unit_id = unit_id;
    }

    // Getters and setters
    public Integer getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public Integer getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        this.unit_id = unit_id;
    }

    // equals and hashCode for proper behavior in collections and JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drug_UnitId)) return false;
        Drug_UnitId that = (Drug_UnitId) o;
        return Objects.equals(drug_id, that.drug_id) && Objects.equals(unit_id, that.unit_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drug_id, unit_id);
    }
}
