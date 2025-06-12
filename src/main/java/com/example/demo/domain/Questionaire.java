package com.example.demo.domain;

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
@Table(name = "questionaire")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Questionaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer naireId;

    private String question;
    private Boolean smoke;
    private Integer milk;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;
}
