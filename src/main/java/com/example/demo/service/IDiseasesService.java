package com.example.demo.service;

import com.example.demo.domain.Diseases;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.diseases.GetDiseasesDTO;

public interface IDiseasesService {
    // Get all diseases
    public ResultPaginationDTO getDiseases(GetDiseasesDTO getDiseasesDTO);

    // Get disease by id
    public Diseases getDiseaseByDiseaseId(Long id);

    // Check exist disease by id
    public boolean existByDiseaseId(Long id);

    public boolean existByDiseaseName(String diseaseName);

    // Create and edit disease
    public Diseases saveDisease(Diseases disease);

    // Delete disease
    public String deleteDisease(Long id);
}
