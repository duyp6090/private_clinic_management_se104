package com.example.demo.service;

import com.example.demo.domain.Drugs;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateDrug;
import com.example.demo.dto.drugs.requestDrugs.EditDrug;
import com.example.demo.dto.drugs.requestDrugs.GetDrugs;

public interface IDrugsService {
    // Get List drugs by filter information
    ResultPaginationDTO getListDrugs(GetDrugs getDrugs);

    // Create drug
    public Drugs createDrug(CreateDrug drugs);

    // Edit drug
    public Drugs editDrug(EditDrug drugs, Long drugId);

    // Delete drug by drugId
    public void deleteDrugByDrugId(Long drugId);
}
