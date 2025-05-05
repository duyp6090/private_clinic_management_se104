package com.example.demo.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "examination_detail")
public class ExaminationDetail {

    @EmbeddedId
    private ExaminationKey id;

    private long quantity;
    private String note;
    private double exportPrice;
    private double totalPrice;

    public ExaminationDetail(ExaminationKey id) {
        this.id = id;
    }

    public ExaminationDetail() {

    }

    @ManyToOne
    @MapsId("examinationId")
    @JoinColumn(name = "examination_id", referencedColumnName = "examinationId")
    private Examination examination;

    @ManyToOne
    @MapsId("drugId")
    @JoinColumn(name = "drug_id", referencedColumnName = "drugId")
    private Drugs drug;

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public Drugs getDrugs() {
        return drug;
    }

    public void setDrugs(Drugs drugs) {
        this.drug = drugs;
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

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ExaminationKey getId() {
        return id;
    }

    public void setId(ExaminationKey id) {
        this.id = id;
    }

}
