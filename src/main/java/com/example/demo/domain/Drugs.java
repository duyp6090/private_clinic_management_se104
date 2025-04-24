/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author iset1enloc
 */
@Entity
@Table(name = "tbl_drugs")
public class Drugs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Long drug_id;
    
    
    public Drugs(String drug_name, String description, Long quantity, double imported_price,
            double selling_price, Date imported_date, Date expiration_date) {
        this.drug_name = drug_name;
        this.description = description;
        this.quantity = quantity;
        this.imported_price = imported_price;
        this.selling_price = selling_price;
        this.imported_date = imported_date;
        this.expiration_date = expiration_date;
    }
    private String drug_name;
    private String description;
    private Long quantity;
    private double imported_price;
    private double selling_price;
    private Date imported_date;
    private Date expiration_date;

    @OneToMany(mappedBy = "drug")
    private Set<Drug_Unit>  drugUnits = new HashSet<>();

    public Long getDrug_id() {
        return drug_id;
    }
    public void setDrug_id(Long drug_id) {
        this.drug_id = drug_id;
    }
    public String getDrug_name() {
        return drug_name;
    }
    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public double getImported_price() {
        return imported_price;
    }
    public void setImported_price(double imported_price) {
        this.imported_price = imported_price;
    }
    public double getSelling_price() {
        return selling_price;
    }
    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }
    public Date getImported_date() {
        return imported_date;
    }
    public void setImported_date(Date imported_date) {
        this.imported_date = imported_date;
    }
    public Date getExpiration_date() {
        return expiration_date;
    }
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
    

}
