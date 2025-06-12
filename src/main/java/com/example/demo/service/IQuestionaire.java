package com.example.demo.service;

import com.example.demo.dto.QuestionaireDTO;

public interface IQuestionaire {

    QuestionaireDTO createQuestionaire(Integer milk, Boolean smoke, Long patientId);
}
