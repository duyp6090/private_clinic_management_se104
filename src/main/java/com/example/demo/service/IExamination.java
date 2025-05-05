package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.Examination;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.examination.requestExamination.UpdateListDrugsRecord;
import com.example.demo.dto.examination.requestExamination.GetPatientExamination;
import com.example.demo.dto.examination.requestExamination.GetWaitingExamination;
import com.example.demo.dto.examination.requestExamination.UpdateExamination;
import com.example.demo.dto.examination.responseExamination.ResponseBill;
import com.example.demo.dto.examination.responseExamination.ResponseExaminationRecord;

public interface IExamination {
    // Get person to patient
    ResultPaginationDTO getPersonsWaitPatients(GetWaitingExamination getWaitingExamination);

    // Get patient
    ResultPaginationDTO getPatientsExamination(GetPatientExamination getPatientExamination);

    // Get bill by id
    ResponseBill getExaminationBill(Long id);

    List<Examination> getExaminationByIsExamAndExaminationDate(boolean isExam,
            LocalDate examinationDate);

    // Get record examination detail
    ResponseExaminationRecord getRecordExamination(Long examinationId);

    // Update examination detail
    String updateExamination(UpdateExamination updateExamination, Long examinationId);

    // Update record examination detail
    String updateDrugRecordExamination(List<UpdateListDrugsRecord> updateListDrugsRecord, Long examinationId);

    // Create examination record
    Examination createExamination(Examination examination);
}
