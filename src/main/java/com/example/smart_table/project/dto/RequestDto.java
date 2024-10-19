package com.example.smart_table.project.dto;

import jakarta.servlet.http.HttpServletRequest;
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
public class RequestDto {


    private String sort;

    private Set<Long> resources;

    private Set<Long> managers;

    private int pagination;

    HttpServletRequest request;
}