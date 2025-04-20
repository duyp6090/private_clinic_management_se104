package com.example.demo.service.service_implementation;

import com.example.demo.domain.Parameter;
import com.example.demo.dto.parameter.EditParameter;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.ParameterRepository;
import com.example.demo.service.IParameterService;

import jakarta.validation.Valid;

public class ParameterServiceImpl implements IParameterService {

    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> getParameter() {
        List<Parameter> parameters = parameterRepository.findAll();
        if (parameters.isEmpty()) {
            return null;
        }
        return parameters;
    }

    @Override
    public Parameter editParameter(@Valid @RequestParam EditParameter parameter) {
        // Get parameter
        List<Parameter> parameters = this.getParameter();
        if (parameters == null || parameters.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        Parameter firstParameter = parameters.get(0);

        if (parameter.getNumber_patient_max() != null) {
            firstParameter.setNumber_patient_max(parameter.getNumber_patient_max());
        }
        if (parameter.getExam_fee() != null) {
            firstParameter.setExam_fee(parameter.getExam_fee());
        }
        if (parameter.getDrug_fee_percent() != null) {
            firstParameter.setDrug_fee_percent(parameter.getDrug_fee_percent());
        }
        // Save parameter
        return this.parameterRepository.save(firstParameter);
    }
}
