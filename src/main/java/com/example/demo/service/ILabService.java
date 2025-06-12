package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LaboratoryDTO;

public interface ILabService {
    List<LaboratoryDTO> getAllLabs(Long patientId);

}
