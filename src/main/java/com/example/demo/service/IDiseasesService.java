package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Diseases;

public interface IDiseasesService {
    // Get all diseases
    public List<Diseases> getDiseases();

    // Get disease by id
    public Diseases getDiseaseById(Long id);

    // Check exist disease by id
    public boolean existById(Long id);

    public boolean existByDiseaseName(String diseaseName);

    // Create and edit disease
    public Diseases saveDiseases(Diseases disease);

    // Delete disease
    public String deleteDiseases(Long id); // id is the disease id
}
