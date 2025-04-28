package com.example.demo.service;

import com.example.demo.domain.DrugsUnit;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.EditUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.GetUnitDrug;

public interface IDrugsUnitService {
    // Get all drugUnit
    public ResultPaginationDTO getAllDrugsUnit(GetUnitDrug getUnitDrug);

    // Get drugUnit by id
    public DrugsUnit getDrugUnitById(Long id);

    // Create and update drugUnit
    public DrugsUnit addDrugUnit(CreateUnitDrug createUnitDrug);

    public DrugsUnit editDrugUnit(EditUnitDrug editUnitDrug, Long id);

    public boolean existsByDrugUnitName(String unitName);

    public boolean existsByDrugUnitId(Long id);

    // Delete drugUnit
    public void deleteByDrugUnitId(Long id);
}
