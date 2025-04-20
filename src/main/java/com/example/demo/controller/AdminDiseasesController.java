package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Diseases;
import com.example.demo.dto.diseases.CreateDiseasesDTO;
import com.example.demo.dto.diseases.EditDiseasesDTO;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.DiseasesServiceIpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/diseases")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDiseasesController {
    private final DiseasesServiceIpl diseasesServiceIpl;

    public AdminDiseasesController(DiseasesServiceIpl diseasesServiceIpl) {
        this.diseasesServiceIpl = diseasesServiceIpl;
    }

    @GetMapping("/get-diseases")
    public List<Diseases> getDiseases() {
        List<Diseases> diseases = this.diseasesServiceIpl.getDiseases();
        if (diseases.isEmpty()) {
            return null;
        }
        return diseases;
    }

    @PostMapping("/add-diseases")
    public Diseases addDiseases(@Valid @RequestParam CreateDiseasesDTO createDiseasesDTO) {
        // Check if disease already exists
        this.diseasesServiceIpl.existByDiseaseName(createDiseasesDTO.getDisease_name());

        // Create new disease
        Diseases newDisease = new Diseases();
        newDisease.setDisease_name(createDiseasesDTO.getDisease_name());
        newDisease.setDescription(createDiseasesDTO.getDescription());

        return diseasesServiceIpl.saveDiseases(newDisease);
    }

    @PatchMapping("/edit-diseases/{id}") // Get information from the form
    public Diseases editDisease(@Valid @RequestParam EditDiseasesDTO editDiseasesDTO, @PathVariable("id") Long id) {
        Diseases disease = this.diseasesServiceIpl.getDiseaseById(id);

        // Update disease information
        if (editDiseasesDTO.getDisease_name() != null) {
            disease.setDisease_name(editDiseasesDTO.getDisease_name());
        }
        if (editDiseasesDTO.getDescription() != null) {
            disease.setDescription(editDiseasesDTO.getDescription());
        }
        return this.diseasesServiceIpl.saveDiseases(disease);
    }

    @DeleteMapping("/delete-diseases/{id}")
    public ResponseEntity<RestResponse<Object>> deleteDiseases(@PathVariable("id") Long id) {
        // Check if disease exists
        this.diseasesServiceIpl.existById(id);

        // Delete disease and return response
        this.diseasesServiceIpl.deleteDiseases(id);

        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(HttpStatus.OK.value());
        restResponse.setMessage("Call API successfully!");
        restResponse.setData("Delete disease successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }
}
