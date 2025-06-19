package com.example.demo.dto.examination.requestExamination;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateListDrugsRecord {
    @NotNull(message = "QUANTITY_NOT_NULL")
    @Min(value = 1, message = "QUANTITY_INVALID")
    private Long quantity;

    private String note;

    @NotNull(message = "DRUG_ID_NOT_NULL")
    private Long drugId;

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
