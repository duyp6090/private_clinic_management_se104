package com.example.demo.dto.examination.responseExamination;

public class ResponseDrugsRecord {
    private String drugName;
    private String unitName;
    private long quantity;
    private String note;

    public ResponseDrugsRecord(String drugName, String unitName, long quantity, String note) {
        this.drugName = drugName;
        this.unitName = unitName;
        this.quantity = quantity;
        this.note = note;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
