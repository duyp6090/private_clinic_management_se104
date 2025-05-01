package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Diseases;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.diseases.CreateDiseasesDTO;
import com.example.demo.dto.diseases.EditDiseasesDTO;
import com.example.demo.dto.diseases.GetDiseasesDTO;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.DiseasesServiceIpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/diseases")
// @PreAuthorize("hasRole('ADMIN')")
public class AdminDiseasesController {
    private final DiseasesServiceIpl diseasesServiceIpl;

    public AdminDiseasesController(DiseasesServiceIpl diseasesServiceIpl) {
        this.diseasesServiceIpl = diseasesServiceIpl;
    }

    @GetMapping("/get-diseases")
    public ResultPaginationDTO getDiseases(@Valid @ModelAttribute GetDiseasesDTO getDiseasesDTO) {
        ResultPaginationDTO diseases = this.diseasesServiceIpl.getDiseases(getDiseasesDTO);
        return diseases;
    }

    @PostMapping("/add-diseases")
    public Diseases addDiseases(@Valid @ModelAttribute CreateDiseasesDTO createDiseasesDTO) {
        // Check if disease already exists
        this.diseasesServiceIpl.existByDiseaseName(createDiseasesDTO.getDiseaseName());

        // Create new disease
        Diseases newDisease = new Diseases();
        newDisease.setDiseaseName(createDiseasesDTO.getDiseaseName());
        newDisease.setDescription(createDiseasesDTO.getDescription());

        return diseasesServiceIpl.saveDisease(newDisease);
    }

    @PatchMapping("/edit-diseases/{id}") // Get information from the form
    public Diseases editDisease(@Valid @ModelAttribute EditDiseasesDTO editDiseasesDTO, @PathVariable("id") Long id) {
        Diseases disease = this.diseasesServiceIpl.getDiseaseByDiseaseId(id);

        // Update disease information
        if (editDiseasesDTO.getDiseaseName() != null) {
            disease.setDiseaseName(editDiseasesDTO.getDiseaseName());
        }
        if (editDiseasesDTO.getDescription() != null) {
            disease.setDescription(editDiseasesDTO.getDescription());
        }
        return this.diseasesServiceIpl.saveDisease(disease);
    }

    @DeleteMapping("/delete-diseases/{id}")
    public ResponseEntity<RestResponse<Object>> deleteDiseases(@PathVariable("id") Long id) {
        // Check if disease exists
        this.diseasesServiceIpl.existByDiseaseId(id);

        // Delete disease and return response
        this.diseasesServiceIpl.deleteDisease(id);

        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(HttpStatus.OK.value());
        restResponse.setMessage("Call API successfully!");
        restResponse.setData("Delete disease successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }
}
