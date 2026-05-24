package com.example.demo.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "laboratory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Laboratory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer bloodId;


    private String labName;


    private Double result;


    private String unit;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;
}
