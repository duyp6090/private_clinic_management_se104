package com.example.demo.controller;

import com.example.demo.domain.Parameter;
import com.example.demo.dto.parameter.EditParameter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.service_implementation.ParameterServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/parameter")
// @PreAuthorize("hasRole('ADMIN')")
public class AdminParameterController {
    private final ParameterServiceImpl parameterService;

    public AdminParameterController(ParameterServiceImpl parameterServiceImpl) {
        this.parameterService = parameterServiceImpl;
    }

    @GetMapping("/get-parameter")
    public List<Parameter> getParameter() {
        List<Parameter> parameters = parameterService.getParameter();
        return parameters;
    }

    @PatchMapping("/edit-parameter") // Get information from the form
    public Parameter edParameter(@Valid @ModelAttribute EditParameter parameter) {
        // Save parameter
        Parameter newParameter = this.parameterService.editParameter(parameter);
        return newParameter;
    }
}
