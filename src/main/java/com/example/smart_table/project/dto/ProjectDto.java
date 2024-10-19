package com.example.smart_table.project.dto;

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
public class ProjectDto {

    private long id;
    private long pmId;
    private String projectName;
    private String status;
    private long estimation;
    private LocalDateTime lastUpdate;
    private LocalDateTime projectStart;
    private LocalDateTime projectEnd;
    private Set<ResourceDto> resources;
}