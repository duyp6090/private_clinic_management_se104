package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Laboratory;
import com.example.demo.domain.Patients;
import com.example.demo.dto.LaboratoryDTO;
import com.example.demo.repository.LaboratoryRepository;
import com.example.demo.repository.PatientsRepository;
import com.example.demo.service.ILabService;
import com.example.demo.service.IPatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabServiceImpl implements ILabService {
    private final PatientsRepository patientsRepository;
    private final LaboratoryRepository laboratoryRepository;

    @Override
    public List<LaboratoryDTO> getAllLabs(Long patientId) {
        Optional<Patients> patientOpt = patientsRepository.findById(patientId);
        Patients patient = patientOpt.get();
        List<Laboratory> list = laboratoryRepository.findByPatient(patient);
        System.out.println(">>>>>list: " + list);
        List<LaboratoryDTO> listDTO = list.stream()
                .map(lab -> {
                    LaboratoryDTO dto = new LaboratoryDTO();
                    dto.setLabName(lab.getLabName());
                    dto.setResult(lab.getResult());
                    dto.setUnit(lab.getUnit());
                    return dto;
                })
                .collect(Collectors.toList());

        return listDTO;
    }

}
