/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 *
 * @author iset1enloc
 */
@Entity
@Table(name = "tbl_diseases")
public class Diseases {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int disease_id;
    
    private String disease_name;

    private String description;
    public Diseases(){

    }
    public Diseases(String disease_name, String description) {
        this.disease_name = disease_name;
        this.description = description;
    }

    public int getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(int disease_id) {
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
