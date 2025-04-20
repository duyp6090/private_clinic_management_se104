package com.example.demo.controller;

import com.example.demo.domain.Parameter;
import com.example.demo.dto.parameter.EditParameter;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.service_implementation.ParameterServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/parameter")
@PreAuthorize("hasRole('ADMIN')")
public class AdminParameterController {
    private final ParameterServiceImpl parameterService;

    public AdminParameterController(ParameterServiceImpl parameterServiceImpl) {
        this.parameterService = parameterServiceImpl;
    }

    @GetMapping("/get-parameter")
    public Parameter getParameter() {
        List<Parameter> parameters = parameterService.getParameter();
        if (parameters.isEmpty()) {
            return null;
        }
        return parameters.get(0);
    }

    @PatchMapping("/edit-parameter") // Get infromation from the form
    public Parameter edParameter(@Valid @RequestParam EditParameter parameter) {
        // Save parameter
        Parameter newParameter = this.parameterService.editParameter(parameter);
        return newParameter;
    }
}
