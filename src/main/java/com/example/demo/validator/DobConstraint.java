package com.example.demo.validator;

import java.lang.annotation.Target;

import com.example.demo.validator.validatorClass.DobValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DobValidator.class })
public @interface DobConstraint {
    String message() default "jakarta.validation.constraints.Size.message";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
