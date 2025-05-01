package com.example.demo.validator.validatorClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import com.example.demo.validator.DobConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {

    private int minAge;

    @Override
    public boolean isValid(LocalDate arg0, ConstraintValidatorContext arg1) {
        if (Objects.isNull(arg0)) {
            return true;
        }

        long year = ChronoUnit.YEARS.between(arg0, LocalDate.now());

        return year >= minAge;
    }

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.minAge = constraintAnnotation.min();
    }
}
