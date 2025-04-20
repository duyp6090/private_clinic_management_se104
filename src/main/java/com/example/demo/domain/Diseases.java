package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diseases")
public class Diseases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long disease_id;
    private String disease_name;
    private String description;

    public long getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(long disease_id) {
        this.disease_id = disease_id;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
