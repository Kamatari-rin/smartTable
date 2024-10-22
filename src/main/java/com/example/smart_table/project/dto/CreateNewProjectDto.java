package com.example.smart_table.project.dto;

import com.example.smart_table.project.entity.enums.ProjectStatus;
import com.example.smart_table.project.entity.enums.ResourceType;
import com.example.smart_table.project.validation.enumValidator.EnumValid;
import com.example.smart_table.project.validation.dateValidator.ValidDateRange;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ValidDateRange
public class CreateNewProjectDto {
    @NotNull(message = "Project Manager ID cannot be null.")
    private long pmId;

    @NotNull(message = "Project name cannot be null.")
    private  String projectName;

    @NotNull(message = "Status cannot be null.")
    @EnumValid(enumClass = ProjectStatus.class, message = "Invalid project status.")
    private String status;

    @NotNull(message = "Resources cannot be null.")
    @EnumValid(enumClass = ResourceType.class, message = "Invalid resource type.")
    @Size(min = 1, message = "At least one resource must be provided.")
    private Set<ResourceType> resources;

    @NotNull(message = "Estimate cannot be null.")
    @Positive(message = "Estimate must be a positive value.")
    private long estimate;

    private LocalDateTime lastUpdate = LocalDateTime.now();

    @NotNull(message = "Project start time cannot be null.")
    @FutureOrPresent(message = "Project start time must be in the future or present.")
    private LocalDateTime projectStart;

    @NotNull(message = "Project end time cannot be null.")
    @Future(message = "Project end time must be in the future.")
    private LocalDateTime projectEnd;
}
