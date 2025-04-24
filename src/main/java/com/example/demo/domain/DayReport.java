/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import java.time.Year;

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
@Table(name = "tbl_day_report")
public class DayReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ngay;
    private int thang;
    private Year nam;

    private float number_of_patients;
    private float revenue;
    private float ratio;

    public DayReport() {}

    public DayReport(int ngay, int thang, Year nam, float number_of_patients, float revenue, float ratio) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.number_of_patients = number_of_patients;
        this.revenue = revenue;
        this.ratio = ratio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public Year getNam() {
        return nam;
    }

    public void setNam(Year nam) {
        this.nam = nam;
    }

    public float getNumber_of_patients() {
        return number_of_patients;
    }

    public void setNumber_of_patients(float number_of_patients) {
        this.number_of_patients = number_of_patients;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    
}

