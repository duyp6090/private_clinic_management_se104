package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Examination;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.examination.requestExamination.GetBillExamination;
import com.example.demo.dto.examination.requestExamination.GetPatientExamination;
import com.example.demo.dto.examination.requestExamination.GetWaitingExamination;
import com.example.demo.dto.examination.requestExamination.UpdateExamination;
import com.example.demo.dto.examination.requestExamination.UpdateListDrugsRecord;
import com.example.demo.dto.examination.responseExamination.ResponseBill;
import com.example.demo.dto.examination.responseExamination.ResponseExaminationRecord;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.ExaminationImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/examination")
public class ExaminationController {
    private final ExaminationImpl examinationImpl;

    public ExaminationController(ExaminationImpl examinationImpl) {
        this.examinationImpl = examinationImpl;
    }

    @GetMapping("/waiting")
    public ResultPaginationDTO getPersonWaiting(
            @Valid @ModelAttribute GetWaitingExamination getWaitingExamination) {
        ResultPaginationDTO result = examinationImpl.getPersonsWaitPatients(getWaitingExamination);
        return result;
    }

    @GetMapping("/patients")
    public ResultPaginationDTO getPatientsExamination(
            @Valid @ModelAttribute GetPatientExamination getPatientsExamination) {
        ResultPaginationDTO result = examinationImpl.getPatientsExamination(getPatientsExamination);
        return result;
    }

    @GetMapping("/getAll-bill")
    public List<ResponseBill> getBill(
            @Valid @ModelAttribute GetBillExamination getBillExamination) {
        List<ResponseBill> result = this.examinationImpl.getExaminationBill(getBillExamination);
        return result;
    }

    @GetMapping("/record-examination/{examinationId}")
    public ResponseExaminationRecord getRecordExamination(
            @PathVariable(name = "examinationId") Long examinationId) {
        ResponseExaminationRecord result = this.examinationImpl.getRecordExamination(examinationId);
        return result;
    }

    @PostMapping("/create-examination/{id}")
    public Examination createExamination(
            @PathVariable("id") Long id) {
        Examination result = this.examinationImpl.createExamination(id);
        return result;
    }

    @PatchMapping("/update-examination/{examinationId}")
    public ResponseEntity<RestResponse<Object>> updateExamination(
            @PathVariable(name = "examinationId") Long examinationId,
            @Valid @ModelAttribute UpdateExamination updateExamination) {
        String result = this.examinationImpl.updateExamination(updateExamination, examinationId);

        RestResponse<Object> response = new RestResponse<>();
        response.setMessage("Call API successfully");
        response.setStatusCode(200);
        response.setData(result);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-record-examination/{examinationId}")
    public ResponseEntity<RestResponse<Object>> updateRecordExamination(
            @PathVariable(name = "examinationId") Long examinationId,
            @Valid @RequestBody List<UpdateListDrugsRecord> updateListDrugsRecord) {
        String result = this.examinationImpl.updateDrugRecordExamination(updateListDrugsRecord, examinationId);

        RestResponse<Object> response = new RestResponse<>();
        response.setMessage("Call API successfully");
        response.setStatusCode(200);
        response.setData(result);

        return ResponseEntity.ok(response);
    }
}
