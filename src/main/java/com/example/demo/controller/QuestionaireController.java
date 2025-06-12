package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionaireDTO;
import com.example.demo.service.IQuestionaire;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/questionaire")
@RequiredArgsConstructor
public class QuestionaireController {
    private final IQuestionaire iQuestionaire;

    @PostMapping("/create-questionaire/{patientId}")
    public QuestionaireDTO createQuestionaireLab(@RequestParam Integer milk, @RequestParam Boolean smoke,
            @PathVariable Long patientId) {
        QuestionaireDTO naireDTO = iQuestionaire.createQuestionaire(milk, smoke, patientId);
        return naireDTO;
    }
}
