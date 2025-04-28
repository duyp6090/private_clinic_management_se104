package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Drugs;
import com.example.demo.domain.DrugsUnit;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.drugs.requestDrugs.CreateDrug;
import com.example.demo.dto.drugs.requestDrugs.EditDrug;
import com.example.demo.dto.drugs.requestDrugs.GetDrugs;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DrugsRepository;
import com.example.demo.repository.DrugsUnitRepository;
import com.example.demo.service.IDrugsService;

@Service
public class DrugsServiceImpl implements IDrugsService {
    private final DrugsRepository drugsRepository;
    private final DrugsUnitRepository drugsUnitRepository;
    private final List<String> VALID_SORT_FIELDS = List.of("drugName", "quantity", "importPrice", "expirationDate");

    public DrugsServiceImpl(DrugsRepository drugsRepository, DrugsUnitRepository drugsUnitRepository) {
        this.drugsRepository = drugsRepository;
        this.drugsUnitRepository = drugsUnitRepository;
    }

    public ResultPaginationDTO getListDrugs(GetDrugs getDrugs) {
        // Create result
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        // Pagination, sorting and filtering
        int page = getDrugs.getPage();
        int size = getDrugs.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        if (getDrugs.getSort() != null) {
            String[] sortFields = getDrugs.getSort().split("\\|");
            for (String sortParams : sortFields) {
                String[] sortField = sortParams.split(",");
                String field = sortField[0];
                String typeSort = sortField[1];
                if (VALID_SORT_FIELDS.contains(field)) {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                    orders.add(order);
                }
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));

        // Get and return data
        Page<Drugs> listDrugs = this.drugsRepository.findAll(getDrugs, pageable);

        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(listDrugs.getTotalPages());
        paginationDTO.setTotalElements((int) listDrugs.getTotalElements());

        result.setPaginationDTO(paginationDTO);
        result.setData(listDrugs.getContent());

        return result;
    }

    public Drugs createDrug(CreateDrug createDrug) {
        // Check name exist
        boolean isExistDrugName = this.drugsRepository.existsByDrugName(createDrug.getDrugName());
        if (isExistDrugName) {
            throw new AppException(ErrorCode.INFORMATION_EXISTS);
        }
        // Get drug unit by unitId
        DrugsUnit drugsUnit = this.drugsUnitRepository.findById(createDrug.getUnitId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        // Create drug
        Drugs drugs = new Drugs();
        drugs.setDrugName(createDrug.getDrugName());
        drugs.setDescription(createDrug.getDescription());
        drugs.setQuantity(createDrug.getQuantity());
        drugs.setImportPrice(createDrug.getImportPrice());
        drugs.setExpirationDate(createDrug.getExpirationDate());
        drugs.setDrugsUnit(drugsUnit);

        // Save drug
        return this.drugsRepository.save(drugs);
    }

    public Drugs editDrug(EditDrug editDrug, Long drugId) {
        // get drug by drugId
        Drugs drug = this.drugsRepository.findById(drugId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        // Check name exist
        boolean isExistDrugName = this.drugsRepository.existsByDrugName(editDrug.getDrugName());
        if (isExistDrugName) {
            throw new AppException(ErrorCode.INFORMATION_EXISTS);
        }

        // Update drug
        if (editDrug.getDrugName() != null) {
            drug.setDrugName(editDrug.getDrugName());
        }
        if (editDrug.getDescription() != null) {
            drug.setDescription(editDrug.getDescription());
        }
        if (editDrug.getQuantity() != null) {
            drug.setQuantity(editDrug.getQuantity());
        }
        if (editDrug.getImportPrice() != null) {
            drug.setImportPrice(editDrug.getImportPrice());
        }
        if (editDrug.getExpirationDate() != null) {
            drug.setExpirationDate(editDrug.getExpirationDate());
        }
        if (editDrug.getUnitId() != null) {
            DrugsUnit drugsUnit = this.drugsUnitRepository.findById(editDrug.getUnitId())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            drug.setDrugsUnit(drugsUnit);
        }

        // Save drug
        return this.drugsRepository.save(drug);
    }

    public void deleteDrugByDrugId(Long drugId) {
        // Check drugId exist
        boolean isExistDrugId = this.drugsRepository.existsById(drugId);
        if (!isExistDrugId) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        // Delete drug by drugId
        this.drugsRepository.deleteById(drugId);
    }
}
