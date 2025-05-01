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

import com.example.demo.domain.Drugs;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateDrug;
import com.example.demo.dto.drugs.requestDrugs.EditDrug;
import com.example.demo.dto.drugs.requestDrugs.GetDrugs;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.DrugsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/drugs")
// @PreAuthorize("hasRole('ADMIN')")
public class DrugsController {
    private final DrugsServiceImpl drugsServiceImpl;

    public DrugsController(DrugsServiceImpl drugsServiceImpl) {
        this.drugsServiceImpl = drugsServiceImpl;
    }

    @GetMapping("/get-drugs")
    public ResultPaginationDTO getDrugs(@Valid @ModelAttribute GetDrugs getDrugs) {
        ResultPaginationDTO result = this.drugsServiceImpl.getListDrugs(getDrugs);
        return result;
    }

    @PostMapping("/create-drug")
    public Drugs createDrug(@Valid @ModelAttribute CreateDrug drugs) {
        Drugs newDrugs = this.drugsServiceImpl.createDrug(drugs);
        return newDrugs;
    }

    @PatchMapping("/edit-drug/{drugId}")
    public Drugs editDrug(@Valid @ModelAttribute EditDrug drug, @PathVariable("drugId") Long drugId) {
        Drugs newDrugs = this.drugsServiceImpl.editDrug(drug, drugId);
        return newDrugs;
    }

    @DeleteMapping("/delete-drug/{drugId}")
    public ResponseEntity<RestResponse<Object>> deleteDrug(@PathVariable("drugId") Long drugId) {
        this.drugsServiceImpl.deleteDrugByDrugId(drugId);
        RestResponse<Object> response = new RestResponse<>();
        response.setMessage("CALL API DELETE DRUG SUCCESS");
        response.setStatusCode(200);
        response.setData("Delete drug successfully");

        return ResponseEntity.ok(response);
    }
}
