package com.example.demo.service.service_implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Patients;
import com.example.demo.domain.Questionaire;
import com.example.demo.dto.QuestionaireDTO;
import com.example.demo.repository.PatientsRepository;
import com.example.demo.repository.QuestionaireRepository;
import com.example.demo.service.IQuestionaire;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionaireImpl implements IQuestionaire {

    private final QuestionaireRepository questionaireRepository;
    private final PatientsRepository patientsRepository;

    @Override
    public QuestionaireDTO createQuestionaire(Integer milk, Boolean smoke, Long patientId) {
        Optional<Patients> patientOpt = patientsRepository.findById(patientId);
        Patients patient = patientOpt.get();
        Questionaire naire = Questionaire.builder()
                .milk(milk)
                .smoke(smoke)
                .patient(patient)
                .build();
        questionaireRepository.save(naire);
        QuestionaireDTO naireDTO = new QuestionaireDTO(milk, smoke);
        return naireDTO;
    }

}
