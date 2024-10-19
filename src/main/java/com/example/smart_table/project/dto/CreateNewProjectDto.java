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
public class CreateNewProjectDto {
    private long pmId;
    private  String projectName;
    private String status;
    private Set<String> resources;
    private long estimate;
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private LocalDateTime projectStart;
    private  LocalDateTime projectEnd;

}
