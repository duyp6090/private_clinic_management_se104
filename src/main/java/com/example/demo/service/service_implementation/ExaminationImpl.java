package com.example.demo.service.service_implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Diseases;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Drugs;
import com.example.demo.domain.Examination;
import com.example.demo.domain.ExaminationDetail;
import com.example.demo.domain.ExaminationKey;
import com.example.demo.domain.Parameter;
import com.example.demo.domain.User;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.examination.requestExamination.UpdateListDrugsRecord;
import com.example.demo.dto.examination.requestExamination.GetBillExamination;
import com.example.demo.dto.examination.requestExamination.GetPatientExamination;
import com.example.demo.dto.examination.requestExamination.GetWaitingExamination;
import com.example.demo.dto.examination.requestExamination.UpdateExamination;
import com.example.demo.dto.examination.responseExamination.ResponseBill;
import com.example.demo.dto.examination.responseExamination.ResponseDrugsRecord;
import com.example.demo.dto.examination.responseExamination.ResponseExaminationRecord;
import com.example.demo.dto.examination.responseExamination.ResponsePatientExamination;
import com.example.demo.dto.examination.responseExamination.ResponseWaitingExamination;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DiseasesRepository;
import com.example.demo.repository.DrugsRepository;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IExamination;

@Service
public class ExaminationImpl implements IExamination {
    private final ExaminationRepository examinationRepository;
    private final DrugsRepository drugsRepository;
    private final ParameterServiceImpl parameterServiceImpl;
    private final UserRepository userRepository;
    private final DiseasesRepository diseasesRepository;
    private final List<String> VALID_SORT_FIELDS = List.of("fullName");

    public ExaminationImpl(ExaminationRepository examinationRepository, DrugsRepository drugsRepository,
            ParameterServiceImpl parameterServiceImpl, UserRepository userRepository,
            DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
        this.userRepository = userRepository;
        this.parameterServiceImpl = parameterServiceImpl;
        this.examinationRepository = examinationRepository;
        this.drugsRepository = drugsRepository;
    }

    @Override
    public ResultPaginationDTO getPersonsWaitPatients(GetWaitingExamination getWaitingExamination) {
        // Create return result
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        int page = getWaitingExamination.getPage();
        int size = getWaitingExamination.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        if (getWaitingExamination.getSort() != null) {
            String[] sortFields = getWaitingExamination.getSort().split("\\|");
            for (String sortField : sortFields) {
                String[] sortParams = sortField.split(",");
                String field = sortParams[0];
                String typeSort = sortParams[1];
                if (VALID_SORT_FIELDS.contains(field)) {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                    orders.add(order);
                }
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<ResponseWaitingExamination> waitingExamination = examinationRepository
                .findWaitingPatient(getWaitingExamination, pageable);

        // Return result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(waitingExamination.getTotalPages());
        paginationDTO.setTotalElements((int) waitingExamination.getTotalElements());

        result.setPaginationDTO(paginationDTO);
        result.setData(waitingExamination.getContent());

        return result;
    }

    @Override
    public ResultPaginationDTO getPatientsExamination(GetPatientExamination getPatientExamination) {
        // Create return result
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        int page = getPatientExamination.getPage();
        int size = getPatientExamination.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        if (getPatientExamination.getSort() != null) {
            String[] sortFields = getPatientExamination.getSort().split("\\|");
            for (String sortField : sortFields) {
                String[] sortParams = sortField.split(",");
                String field = sortParams[0];
                String typeSort = sortParams[1];
                if (VALID_SORT_FIELDS.contains(field)) {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                    orders.add(order);
                }
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<ResponsePatientExamination> patientsExamination = examinationRepository
                .findPatientExamination(getPatientExamination, pageable);

        // Return result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(patientsExamination.getTotalPages());
        paginationDTO.setTotalElements((int) patientsExamination.getTotalElements());

        result.setPaginationDTO(paginationDTO);
        result.setData(patientsExamination.getContent());

        return result;
    }

    @Override
    public List<ResponseBill> getExaminationBill(GetBillExamination getBillExamination) {
        // Get examination
        List<Examination> listExamination = this.examinationRepository
                .findAllBills(getBillExamination.getStartDate(), getBillExamination.getEndDate(),
                        getBillExamination.getFullName());

        // Create list response bill
        List<ResponseBill> listResponseBill = new ArrayList<>();
        for (Examination examination : listExamination) {
            ResponseBill responseBill = new ResponseBill(
                    examination.getExaminationId(),
                    examination.getPatient().getFullName(),
                    examination.getExaminationDate(),
                    examination.getDrugsFee(),
                    examination.getExamFee());
            listResponseBill.add(responseBill);
        }

        // Return result
        return listResponseBill;

    }

    @Override
    public Examination createExamination(Examination examination) {
        return this.examinationRepository.save(examination);
    }

    @Override
    public List<Examination> getExaminationByIsExamAndExaminationDate(
            boolean isExam,
            LocalDate examinationDate) {
        return this.examinationRepository.findByIsExamAndExaminationDate(isExam, examinationDate);
    }

    @Override
    public String updateExamination(UpdateExamination updateExamination, Long examinationId) {
        // Get examination by id
        Examination examination = this.examinationRepository.findByExaminationId(examinationId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND));

        // Update examination information
        if (updateExamination.getSymptoms() != null) {
            examination.setSymptoms(updateExamination.getSymptoms());
        }
        if (updateExamination.getStaffId() != null) {
            User user = this.userRepository.findById(updateExamination.getStaffId()).orElseThrow(
                    () -> new AppException(ErrorCode.NOT_FOUND));
            examination.setDoctor((Doctor) user);
        }
        if (updateExamination.getDiseaseId() != null) {
            Diseases disease = this.diseasesRepository.findById(updateExamination.getDiseaseId()).orElseThrow(
                    () -> new AppException(ErrorCode.NOT_FOUND));
            examination.setDisease(disease);
        }

        examination.setExam(updateExamination.isExam() ? true : false);

        // Save examination record
        this.examinationRepository.save(examination);

        return "Update successfully!";
    }

    @Override
    public ResponseExaminationRecord getRecordExamination(Long examinationId) {
        // Get examination by id
        Examination examination = this.examinationRepository.findByExaminationId(examinationId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND));
        List<ExaminationDetail> listExaminationDetails = examination.getExaminationDetails();

        // Get information of examination detail
        List<ResponseDrugsRecord> listDrugsRecord = new ArrayList<>();
        for (ExaminationDetail examinationDetail : listExaminationDetails) {
            Drugs drug = examinationDetail.getDrugs();
            String drugName = drug.getDrugName();
            String drugUnit = drug.getDrugsUnit().getUnitName();
            long quantity = examinationDetail.getQuantity();
            String note = examinationDetail.getNote();

            ResponseDrugsRecord drugRecord = new ResponseDrugsRecord(
                    drugName, drugUnit, quantity, note);
            listDrugsRecord.add(drugRecord);
        }

        // Get information of examination
        String fullName = examination.getPatient().getFullName();
        String diseaseName = examination.getDisease() == null ? null : examination.getDisease().getDiseaseName();
        LocalDate examinationDate = examination.getExaminationDate();
        String symptoms = examination.getSymptoms();

        // Create response examination record
        ResponseExaminationRecord responseExaminationRecord = new ResponseExaminationRecord(
                examinationId,
                fullName,
                examinationDate,
                symptoms,
                diseaseName,
                listDrugsRecord);
        return responseExaminationRecord;
    }

    @Override
    public String updateDrugRecordExamination(List<UpdateListDrugsRecord> updateListDrugsRecord, Long examinationId) {
        // Get parameter
        Parameter parameter = this.parameterServiceImpl.getParameter().get(0);
        double drugFeePercent = parameter.getDrugFeePercent();

        // Get examination by id
        Examination examination = this.examinationRepository.findByExaminationId(examinationId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND));
        List<ExaminationDetail> listExaminationDetails = examination.getExaminationDetails();

        // Get list drugs to check condition
        List<Drugs> listDrugs = this.drugsRepository.findAll();

        HashMap<Long, Drugs> listDrugsIdAndQuantity = new HashMap<>();
        listDrugs.stream().forEach(drug -> {
            listDrugsIdAndQuantity.put(drug.getDrugId(), drug);
        });

        List<Drugs> updatedDrugs = new ArrayList<>();

        Double totalPrice = 0.0;

        // Loop through list examination drugs record to update
        List<ExaminationDetail> deletedExaminationDetail = new ArrayList<>();
        for (ExaminationDetail examinationDetail : listExaminationDetails) {
            // Get drug id and quantity
            Long drugId = examinationDetail.getId().getDrugId();
            long quantity = examinationDetail.getQuantity();

            // Get drug information
            Drugs drug = listDrugsIdAndQuantity.get(drugId);
            double drugFee = drug.getImportPrice();
            double exportPrice = drugFee * drugFeePercent;

            // check drug is exist in updateListDrugsRecord
            UpdateListDrugsRecord updatedDrugRecord = updateListDrugsRecord.stream()
                    .filter(updateDrug -> updateDrug.getDrugId().equals(drugId))
                    .findAny()
                    .orElse(null);

            if (updatedDrugRecord != null) {
                // Get new quantity
                long newQuantity = updatedDrugRecord.getQuantity();

                if (drug.getQuantity() - (newQuantity - quantity) < 0) {
                    throw new AppException(ErrorCode.NOT_ENOUGH_QUANTITY);
                }

                // Update drug quantity
                drug.setQuantity(drug.getQuantity() - (newQuantity - quantity));

                // Update drug record
                examinationDetail.setQuantity(newQuantity);
                examinationDetail.setExportPrice(exportPrice);
                examinationDetail.setTotalPrice(exportPrice * newQuantity);
                if (updatedDrugRecord.getNote() != null)
                    examinationDetail.setNote(updatedDrugRecord.getNote());

                totalPrice += examinationDetail.getTotalPrice();
            } else {
                // Delete drug record
                deletedExaminationDetail.add(examinationDetail);

                // Update drug quantity
                drug.setQuantity(drug.getQuantity() + quantity);

                totalPrice -= examinationDetail.getTotalPrice();
            }

            updatedDrugs.add(drug);
        }

        // Create new drug record
        for (UpdateListDrugsRecord updateListDrugRecord : updateListDrugsRecord) {
            // Check drug record is not exist in listExaminationDetails
            boolean isExist = listExaminationDetails.stream()
                    .anyMatch(examinationDetail -> examinationDetail.getId().getDrugId() == updateListDrugRecord
                            .getDrugId());

            if (isExist)
                continue;

            // Check drug is exist
            if (!listDrugsIdAndQuantity.containsKey(updateListDrugRecord.getDrugId())) {
                throw new AppException(ErrorCode.NOT_FOUND);
            }

            // Get drug information
            Drugs drug = listDrugsIdAndQuantity.get(updateListDrugRecord.getDrugId());
            double drugFee = drug.getImportPrice();
            double exportPrice = drugFee * drugFeePercent;

            // Check drug quantity is enough
            if (drug.getQuantity() < updateListDrugRecord.getQuantity()) {
                throw new AppException(ErrorCode.NOT_ENOUGH_QUANTITY);
            }

            // Update drug quantity
            drug.setQuantity(drug.getQuantity() - updateListDrugRecord.getQuantity());
            updatedDrugs.add(drug);

            // Create new drug record
            ExaminationKey examinationKey = new ExaminationKey(
                    examinationId,
                    updateListDrugRecord.getDrugId());

            ExaminationDetail examinationDetail = new ExaminationDetail(examinationKey);
            examinationDetail.setQuantity(updateListDrugRecord.getQuantity());
            examinationDetail.setExportPrice(exportPrice);
            examinationDetail.setTotalPrice(exportPrice * updateListDrugRecord.getQuantity());
            if (updateListDrugRecord.getNote() != null)
                examinationDetail.setNote(updateListDrugRecord.getNote());
            examinationDetail.setExamination(examination);
            examinationDetail.setDrugs(drug);

            // Save List drugs record
            listExaminationDetails.add(examinationDetail);

            totalPrice += examinationDetail.getTotalPrice();
        }

        // Remove list drugs record
        for (ExaminationDetail examinationDetail : deletedExaminationDetail) {
            listExaminationDetails.remove(examinationDetail);
        }

        // Save examination record
        examination.setDrugsFee(totalPrice);
        this.examinationRepository.save(examination);

        return "Update successfully!";
    }

}
