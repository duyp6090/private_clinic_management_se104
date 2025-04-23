package com.example.demo.service.service_implementation;

import com.example.demo.domain.Parameter;
import com.example.demo.dto.parameter.EditParameter;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ParameterRepository;
import com.example.demo.service.IParameterService;

@Service
public class ParameterServiceImpl implements IParameterService {

    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> getParameter() {
        List<Parameter> parameters = parameterRepository.findAll();
        return parameters;
    }

    @Override
    public Parameter editParameter(EditParameter parameter) {
        // Get parameter
        List<Parameter> parameters = this.getParameter();
        if (parameters == null || parameters.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        Parameter firstParameter = parameters.get(0);

        if (parameter.getNumberPatientMax() != null) {
            firstParameter.setNumberPatientMax(parameter.getNumberPatientMax());
        }
        if (parameter.getExamFee() != null) {
            firstParameter.setExamFee(parameter.getExamFee());
        }
        if (parameter.getDrugFeePercent() != null) {
            firstParameter.setDrugFeePercent(parameter.getDrugFeePercent());
        }
        // Save parameter
        return this.parameterRepository.save(firstParameter);
    }
}
