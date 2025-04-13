/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author iset1enloc
 */
@Entity
@Table(name = "tbl_units")
public class DrugUnits {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private int drug_unit_id;

    private String drug_name;

    private String drug_description;
    
    @OneToMany(mappedBy = "units")
    private Set<Drug_Unit>  drugUnits = new HashSet<>();

    public DrugUnits(String drug_name, String drug_description) {
        this.drug_name = drug_name;
        this.drug_description = drug_description;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_description() {
        return drug_description;
    }

    public void setDrug_description(String drug_description) {
        this.drug_description = drug_description;
    }

    public int getDrug_unit_id() {
        return drug_unit_id;
    }

    public void setDrug_unit_id(int drug_unit_id) {
        this.drug_unit_id = drug_unit_id;
    }

    
}
