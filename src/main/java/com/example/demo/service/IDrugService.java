/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Drugs;

/**
 *
 * @author iset1enloc
 */
public interface IDrugService {

    // Create
    Drugs createDrug(Drugs drug);

    // Read all
    List<Drugs> getAllDrugs();

    // Read by ID
    Optional<Drugs> getDrugById(Integer id);

    // Update
    Drugs updateDrug(Integer id, Drugs updatedDrug);

    // Delete
    void deleteDrug(Integer id);
}
