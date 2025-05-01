package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Diseases;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.diseases.GetDiseasesDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DiseasesRepository;
import com.example.demo.service.IDiseasesService;

import jakarta.transaction.Transactional;

@Service
public class DiseasesServiceIpl implements IDiseasesService {
    private final DiseasesRepository diseasesRepository;
    private final List<String> VALID_FIELDS_SORT = List.of("diseaseName");

    public DiseasesServiceIpl(DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }

    // Get all diseases
    @Override
    public ResultPaginationDTO getDiseases(GetDiseasesDTO getDiseasesDTO) {
        // Create resultPaginationDTO and PaginationDTO
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        // Pagination, Sorting, Filtering
        int page = getDiseasesDTO.getPage();
        int size = getDiseasesDTO.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        if (getDiseasesDTO.getSort() != null) {
            String[] sortParams = getDiseasesDTO.getSort().split("\\|");
            for (String filed : sortParams) {
                String[] sortParam = filed.split(",");
                String field = sortParam[0];
                String typeSort = sortParam[1];
                if (!VALID_FIELDS_SORT.contains(field)) {
                    throw new AppException(ErrorCode.INVALID_SORT_FIELD);
                }
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                orders.add(order);
            }
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));

        Page<Diseases> diseases = this.diseasesRepository.findAll(getDiseasesDTO, pageable);

        // Set result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(diseases.getTotalPages());
        paginationDTO.setTotalElements((int) diseases.getTotalElements());

        resultPaginationDTO.setPaginationDTO(paginationDTO);
        resultPaginationDTO.setData(diseases.getContent());

        return resultPaginationDTO;
    }

    // Get disease by id
    @Override
    public Diseases getDiseaseByDiseaseId(Long id) {
        Diseases disease = this.diseasesRepository.findByDiseaseId(id).orElse(null);
        if (disease == null) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return disease;
    }

    // Check exist disease by another field
    @Override
    public boolean existByDiseaseId(Long id) {
        boolean existDiseaseById = this.diseasesRepository.existsByDiseaseId(id);
        if (!existDiseaseById) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        return existDiseaseById;
    }

    @Override
    public boolean existByDiseaseName(String diseaseName) {
        boolean existDisease = this.diseasesRepository.existsByDiseaseName(diseaseName);
        if (existDisease) {
            throw new AppException(ErrorCode.INFORMATION_EXISTS);
        }
        return existDisease;
    }

    // Create and edit disease
    @Override
    public Diseases saveDisease(Diseases disease) {
        return this.diseasesRepository.save(disease);
    }

    // Delete disease
    @Transactional
    @Override
    public String deleteDisease(Long id) {
        // Check if disease exists
        boolean existDisease = this.diseasesRepository.existsByDiseaseId(id);
        if (!existDisease) {
            ErrorCode errorCode = ErrorCode.NOT_FOUND;
            AppException appException = new AppException(errorCode);
            throw appException;
        }
        this.diseasesRepository.deleteByDiseaseId(id);
        return "Delete disease successfully!";
    }
}
