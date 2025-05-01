package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DrugsUnit;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.EditUnitDrug;
import com.example.demo.dto.drugs.requestDrugs.GetUnitDrug;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DrugsUnitRepository;
import com.example.demo.service.IDrugsUnitService;

@Service
public class DrugsUnitServiceIml implements IDrugsUnitService {
    private final DrugsUnitRepository drugsUnitRepository;
    private final List<String> VALID_FIELDS_SORT = List.of("unitName");

    public DrugsUnitServiceIml(DrugsUnitRepository drugsUnitRepository) {
        this.drugsUnitRepository = drugsUnitRepository;
    }

    // Get all drugsUnit
    public ResultPaginationDTO getAllDrugsUnit(GetUnitDrug getUnitDrug) {
        // Create result
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        // Paginating, sorting and filtering
        int page = getUnitDrug.getPage();
        int size = getUnitDrug.getSize();
        List<Sort.Order> orders = new ArrayList<>();
        if (getUnitDrug.getSort() != null) {
            String[] sortParams = getUnitDrug.getSort().split("\\|");
            for (String sortParam : sortParams) {
                String[] fields = sortParam.split(",");
                String field = fields[0];
                String typeSort = fields[1];
                if (VALID_FIELDS_SORT.contains(field)) {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                    orders.add(order);
                }
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<DrugsUnit> drugsUnitPage = drugsUnitRepository.findAll(getUnitDrug, pageable);

        // Set result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(drugsUnitPage.getTotalPages());
        paginationDTO.setTotalElements((int) drugsUnitPage.getTotalElements());

        result.setPaginationDTO(paginationDTO);
        result.setData(drugsUnitPage.getContent());

        return result;
    }

    // Get drugsUnit by id
    public DrugsUnit getDrugUnitById(Long id) {
        DrugsUnit drugsUnit = this.drugsUnitRepository.findById(id).orElse(null);
        return drugsUnit;
    }

    // Create and update drugsUnit
    public DrugsUnit addDrugUnit(CreateUnitDrug createUnitDrug) {
        // Check if drugsUnit exists
        boolean existsDrugUnit = this.drugsUnitRepository.existsByUnitName(createUnitDrug.getUnitName());
        if (existsDrugUnit) {
            throw new AppException(ErrorCode.INFORMATION_EXISTS);
        }

        // Create drugsUnit
        DrugsUnit drugsUnit = new DrugsUnit();
        drugsUnit.setUnitName(createUnitDrug.getUnitName());
        drugsUnit.setDescription(createUnitDrug.getDescription());

        // Save drugsUnit
        return drugsUnitRepository.save(drugsUnit);
    }

    public DrugsUnit editDrugUnit(EditUnitDrug editUnitDrug, Long id) {
        // Check if drugsUnit exists
        boolean existsDrugUnit = this.drugsUnitRepository.existsById(id);
        if (!existsDrugUnit) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        // Update drugsUnit
        DrugsUnit drugsUnit = drugsUnitRepository.findById(id).get();
        if (editUnitDrug.getDescription() != null) {
            drugsUnit.setDescription(editUnitDrug.getDescription());
        }
        if (editUnitDrug.getUnitName() != null) {
            // check if drugsUnit exists
            boolean existsDrugUnitName = this.drugsUnitRepository.existsByUnitName(editUnitDrug.getUnitName());
            if (existsDrugUnitName) {
                throw new AppException(ErrorCode.INFORMATION_EXISTS);
            }
            drugsUnit.setUnitName(editUnitDrug.getUnitName());
        }

        // Save drugsUnit
        return drugsUnitRepository.save(drugsUnit);
    }

    // Check if drugsUnit exists by name
    public boolean existsByDrugUnitName(String unitName) {
        return drugsUnitRepository.existsByUnitName(unitName);
    }

    public boolean existsByDrugUnitId(Long id) {
        return drugsUnitRepository.existsById(id);
    }

    // Delete drugsUnit
    public void deleteByDrugUnitId(Long id) {
        // Check if drugsUnit exists
        boolean existsDrugUnit = this.drugsUnitRepository.existsById(id);
        if (!existsDrugUnit) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        // Delete drugsUnit
        drugsUnitRepository.deleteById(id);
    }
}
