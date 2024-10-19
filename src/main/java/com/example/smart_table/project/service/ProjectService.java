package com.example.smart_table.project.service;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getProjectsForPublic(RequestDto request);

    ProjectDto createNewProject(CreateNewProjectDto newProjectDto);

    void deleteProjectById(long projectId);

    List<ProjectDto> search(String text);
}
