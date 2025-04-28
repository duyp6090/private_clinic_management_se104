package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DrugsUnit;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.EditUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.GetUnitDrug;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.DrugsUnitServiceIml;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/drugs-unit")
public class AdminDrugsUnitController {
    private final DrugsUnitServiceIml drugsUnitServiceIml;

    public AdminDrugsUnitController(DrugsUnitServiceIml drugsUnitServiceIml) {
        this.drugsUnitServiceIml = drugsUnitServiceIml;
    }

    @GetMapping("/get-drugs-unit")
    public ResultPaginationDTO getDrugsUnit(@Valid @ModelAttribute GetUnitDrug getUnitDrug) {
        ResultPaginationDTO drugsUnit = this.drugsUnitServiceIml.getAllDrugsUnit(getUnitDrug);
        return drugsUnit;
    }

    @PostMapping("/add-drugs-unit")
    public DrugsUnit addDrugUnit(@Valid @ModelAttribute CreateUnitDrug createUnitDrug) {
        DrugsUnit newDrugUnit = this.drugsUnitServiceIml.addDrugUnit(createUnitDrug);
        return newDrugUnit;
    }

    @PatchMapping("/edit-drugs-unit/{id}") // Get information from the form
    public DrugsUnit editDrugUnit(@Valid @ModelAttribute EditUnitDrug editUnitDrug,
            @PathVariable("id") Long id) {
        DrugsUnit drugsUnit = this.drugsUnitServiceIml.editDrugUnit(editUnitDrug, id);
        return drugsUnit;
    }

    @DeleteMapping("/delete-drugs-unit/{id}")
    public ResponseEntity<RestResponse<Object>> deleteDrugUnit(@PathVariable("id") Long id) {
        // Check if drugUnit exists
        this.drugsUnitServiceIml.existsByDrugUnitId(id);

        this.drugsUnitServiceIml.deleteByDrugUnitId(id);

        RestResponse<Object> response = new RestResponse<>();
        response.setStatusCode(200);
        response.setMessage("CALL API SUCCESSFUL");
        response.setData("Delete drugUnit successfully");

        return ResponseEntity.ok(response);

    }
}
