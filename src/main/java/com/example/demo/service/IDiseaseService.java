/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Diseases;

/**
 *
 * @author iset1enloc
 */
public interface IDiseaseService {
    Diseases saveDiseases(Diseases patient);
    List<Diseases> getAllDiseases();
    Optional<Diseases> getDiseaseById(int id);
    Diseases updateDisease(int  id, Diseases diseases);
    void deleteDisease(int id);
}
