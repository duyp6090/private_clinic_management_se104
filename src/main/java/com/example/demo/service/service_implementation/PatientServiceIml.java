package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Patients;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.patients.GetPatientsDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.PatientsRepository;
import com.example.demo.service.IPatientService;

import jakarta.transaction.Transactional;

@Service
public class PatientServiceIml implements IPatientService {

    private final PatientsRepository patientsRepository;
    private final List<String> VALID_FIELDS_SORT = List.of("fullName", "gender", "yearOfBirth");

    public PatientServiceIml(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    // Get patients by full_name, gender, year_of_birth
    @Override
    public ResultPaginationDTO getPatients(GetPatientsDTO getPatientsDTO) {
        // init resultPaginationDTO
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        // Pagination and sorting, filtering
        int page = getPatientsDTO.getPage();
        int size = getPatientsDTO.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        // Check sort fields validation
        if (getPatientsDTO.getSort() != null) {
            String[] listSortFields = getPatientsDTO.getSort().split("\\|");

            for (String filed : listSortFields) {
                String[] sortedFields = filed.split(",");
                String filedName = sortedFields[0];
                String typeSort = sortedFields[1];
                if (!VALID_FIELDS_SORT.contains(filedName)) {
                    throw new AppException(ErrorCode.INVALID_SORT_FIELD);
                } else {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), filedName);
                    orders.add(order);
                }
            }
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));

        Page<Patients> patients = this.patientsRepository.findPatients(getPatientsDTO, pageable);

        // Return result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(patients.getTotalPages());
        paginationDTO.setTotalElements((int) patients.getTotalElements());

        resultPaginationDTO.setPaginationDTO(paginationDTO);
        resultPaginationDTO.setData(patients.getContent());

        return resultPaginationDTO;
    }

    // Get patient by id
    @Override
    public Patients getPatientByPatientId(Long id) {
        Patients patient = this.patientsRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return patient;
    }

    // Check exist patient by id
    @Override
    public boolean existsByPatientId(Long id) {
        boolean existPatientById = this.patientsRepository.existsByPatientId(id);
        if (!existPatientById) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return existPatientById;
    }

    // Find patient by phone_number or residental_identity
    @Override
    public List<Patients> findByPhoneNumberOrResidentalIdentity(String phoneNumber, String residentalIdentity) {
        List<Patients> patients = this.patientsRepository
                .findByPhoneNumberOrResidentalIdentity(phoneNumber, residentalIdentity);

        for (Patients patient : patients) {
            String phoneNumberPatient = patient.getPhoneNumber();
            String residentalIdentityPatient = patient.getResidentalIdentity();

            if (phoneNumberPatient.equals(phoneNumber)) {
                throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
            }

            if (residentalIdentityPatient.equals(residentalIdentity)) {
                throw new AppException(ErrorCode.RESIDENTAL_IDENTITY_EXISTS);
            }
        }
        return patients;
    }

    // Create and edit patient
    @Override
    public Patients savePatient(Patients patients) {
        return this.patientsRepository.save(patients);
    }

    // Delete patient by id
    @Transactional
    @Override
    public void deletePatientByPatientId(Long id) {
        boolean existPatientById = this.patientsRepository.existsById(id);
        if (!existPatientById) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        this.patientsRepository.deleteByPatientId(id);
    }

    @Override
    public boolean existsByPhoneNumberOrResidentalIdentity(String phoneNumber, String residentalIdentity, Long id) {
        if (phoneNumber != null) {
            Patients existPhoneNumber = this.patientsRepository.findByPhoneNumber(phoneNumber).orElse(null);
            if (existPhoneNumber != null && Long.valueOf(existPhoneNumber.getPatientId()).equals(id)) {
                throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
            }
        }

        if (residentalIdentity != null) {
            Patients existResidentalIdentity = this.patientsRepository.findByResidentalIdentity(residentalIdentity)
                    .orElse(null);
            if (existResidentalIdentity != null && Long.valueOf(existResidentalIdentity.getPatientId()).equals(id)) {
                throw new AppException(ErrorCode.RESIDENTAL_IDENTITY_EXISTS);
            }
        }

        return true;
    }
}
