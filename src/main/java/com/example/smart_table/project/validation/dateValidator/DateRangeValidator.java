package com.example.smart_table.project.validation.dateValidator;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, CreateNewProjectDto> {

    @Override
    public boolean isValid(CreateNewProjectDto dto, ConstraintValidatorContext context) {
        if (dto.getProjectStart() == null || dto.getProjectEnd() == null) {
            return true;
        }
        return dto.getProjectEnd().isAfter(dto.getProjectStart());
    }
}