package com.example.demo.domain;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientId;
    private String fullName;
    private boolean gender;
    private Year yearOfBirth;
    private String address;
    private String phoneNumber;
    private String residentalIdentity;
    private Integer race;
    private Double BMI;
    private Double PIR;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Laboratory> laboratories;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Questionaire> questionaires;

}
