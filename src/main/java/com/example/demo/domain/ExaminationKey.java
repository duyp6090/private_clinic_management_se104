package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ExaminationKey implements Serializable {
    private Long examinationId;
    private Long drugId;

    public Long getExaminationId() {
        return examinationId;
    }

    public ExaminationKey() {
    }

    public ExaminationKey(Long examinationId, Long drugId) {
        this.examinationId = examinationId;
        this.drugId = drugId;
    }

    public void setExaminationId(Long examinationId) {
        this.examinationId = examinationId;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExaminationKey))
            return false;
        ExaminationKey that = (ExaminationKey) o;
        return Objects.equals(examinationId, that.examinationId) &&
                Objects.equals(drugId, that.drugId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examinationId, drugId);
    }
}
