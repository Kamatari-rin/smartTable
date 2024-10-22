package com.example.smart_table.project.validation.enumValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    private Enum<?>[] enumValues;

    @Override
    public void initialize(EnumValid annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}