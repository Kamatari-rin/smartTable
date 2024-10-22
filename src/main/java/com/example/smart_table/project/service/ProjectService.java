package com.example.smart_table.project.service;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    Page<ProjectDto> getProjectsForPublic(RequestDto request);

    ProjectDto createNewProject(CreateNewProjectDto newProjectDto);

    void deleteProjectById(long projectId);

    Page<ProjectDto> search(String query, Pageable pageable);
}
