package com.example.smart_table.project.service.impl;

import com.example.smart_table.exception.ProjectAlreadyExistsException;
import com.example.smart_table.exception.ProjectNotFoundException;
import com.example.smart_table.exception.UserNotFoundException;
import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;
import com.example.smart_table.project.entity.Project;
import com.example.smart_table.project.mapper.ProjectMapper;
import com.example.smart_table.project.repository.ProjectRepository;
import com.example.smart_table.project.service.ProjectService;
import com.example.smart_table.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> getProjectsForPublic(RequestDto request) {
        Page<Project> projectPage = projectRepository.findProjectsForPublic(
                request.getResources(),
                request.getManagers(),
                request.getPagination());

        return projectPage.map(projectMapper::toProjectDto);
    }

    @Override
    @Transactional
    public ProjectDto createNewProject(CreateNewProjectDto newProjectDto) {
        userRepository.findById(newProjectDto.getPmId())
                .orElseThrow(() -> new UserNotFoundException(newProjectDto.getPmId()));

        projectRepository.findByProjectName(newProjectDto.getProjectName())
                .ifPresent(project -> {
                    throw new ProjectAlreadyExistsException(newProjectDto.getProjectName());
                });

        return projectMapper.toProjectDto(
                projectRepository.save(
                        projectMapper.toProject(newProjectDto)
                )
        );
    }

    @Override
    @Transactional
    public void deleteProjectById(long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        projectRepository.delete(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> search(String query, Pageable pageable) {
        return projectRepository.findByProjectNameContainingIgnoreCase(query, pageable)
                .map(projectMapper::toProjectDto);
    }
}
