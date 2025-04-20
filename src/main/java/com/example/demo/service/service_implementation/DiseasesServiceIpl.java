package com.example.demo.service.service_implementation;

import java.util.List;

import com.example.demo.domain.Diseases;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DiseasesRepository;
import com.example.demo.service.IDiseasesService;

public class DiseasesServiceIpl implements IDiseasesService {
    private final DiseasesRepository diseasesRepository;

    public DiseasesServiceIpl(DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }

    // Get all diseases
    @Override
    public List<Diseases> getDiseases() {
        List<Diseases> diseases = this.diseasesRepository.findAll();
        return diseases;
    }

    // Get disease by id
    @Override
    public Diseases getDiseaseById(Long id) {
        Diseases disease = this.diseasesRepository.findById(id).orElse(null);
        if (disease == null) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return disease;
    }

    // Check exist disease by another field
    @Override
    public boolean existById(Long id) {
        boolean existDiseaseById = this.diseasesRepository.existsById(id);
        if (!existDiseaseById) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        return existDiseaseById;
    }

    @Override
    public boolean existByDiseaseName(String diseaseName) {
        boolean existDisease = this.diseasesRepository.existsByDiseaseName(diseaseName);
        if (existDisease) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }
        return existDisease;
    }

    // Create and edit disease
    @Override
    public Diseases saveDiseases(Diseases disease) {
        return this.diseasesRepository.save(disease);
    }

    // Delete disease
    @Override
    public String deleteDiseases(Long id) {
        // Check if disease exists
        boolean existDisease = this.diseasesRepository.existsById(id);
        if (!existDisease) {
            ErrorCode errorCode = ErrorCode.NOT_FOUND;
            AppException appException = new AppException(errorCode);
            throw appException;
        }
        this.diseasesRepository.deleteById(id);
        return "Delete disease successfully!";
    }
}
