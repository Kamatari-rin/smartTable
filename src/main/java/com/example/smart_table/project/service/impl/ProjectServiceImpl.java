package com.example.smart_table.project.service.impl;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;
import com.example.smart_table.project.mapper.ProjectMapper;
import com.example.smart_table.project.repository.ProjectRepository;
import com.example.smart_table.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> getProjectsForPublic(RequestDto request) {

        return projectRepository.findProjectsForPublic(
                        request.getResources().stream().toList(),
                        request.getManagers().stream().toList(), request.getSort())
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectDto createNewProject(CreateNewProjectDto newProjectDto) {
        return projectMapper.toProjectDto(
                projectRepository.save(
                        projectMapper.toProject(newProjectDto)
                )
        );
    }

    @Override
    public void deleteProjectById(long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> search(String text) {
        return projectRepository.findByProjectNameContainingIgnoreCase(text)
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList()
                );
    }
}
