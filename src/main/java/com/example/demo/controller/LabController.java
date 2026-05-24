package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LaboratoryDTO;
import com.example.demo.service.ILabService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lab")
@RequiredArgsConstructor
public class LabController {
    private final ILabService iLabService;

    @GetMapping("/get-all/{patientId}")
    public List<LaboratoryDTO> getAllLabs(@PathVariable Long patientId) {
        List<LaboratoryDTO> list = iLabService.getAllLabs(patientId);
        return list;
    }
}
