/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.DrugUnits;

/**
 *
 * @author iset1enloc
 */
public interface IDrugUnitService {

    // Create
    DrugUnits createDrugUnit(DrugUnits drugUnit);

    // Read all
    List<DrugUnits> getAllDrugUnits();

    // Read by ID (composite key)
    Optional<DrugUnits> getDrugUnitById(int id);

    // Update
    DrugUnits updateDrugUnit(int id, DrugUnits updatedDrugUnit);

    // Delete
    void deleteDrugUnit(int id);
}