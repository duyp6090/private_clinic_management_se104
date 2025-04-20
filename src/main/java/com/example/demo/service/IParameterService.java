package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Parameter;
import com.example.demo.dto.parameter.EditParameter;

public interface IParameterService {
    // Method get parameter
    public List<Parameter> getParameter();

    // Method edit parameter
    public Parameter editParameter(EditParameter parameter);
}
