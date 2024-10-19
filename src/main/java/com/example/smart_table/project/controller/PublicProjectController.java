package com.example.smart_table.project.controller;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;
import com.example.smart_table.project.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Validated
public class PublicProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjectsPublic(
            @RequestParam(required = false) Set<Long> resources,
            @RequestParam(required = false) Set<Long> managers,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest httpRequest) {

        sort = Optional.ofNullable(sort)
                .filter(s -> s.equals("id") || s.equals("name") || s.equals("lastUpdate"))
                .orElse("id");

        return ResponseEntity.ok(
                    projectService.getProjectsForPublic(
                        RequestDto.builder()
                                .resources(Optional.ofNullable(resources).orElse(new HashSet<>()))
                                .managers(Optional.ofNullable(managers).orElse(new HashSet<>()))
                                .sort(sort)
                                .pagination(size)
                                .request(httpRequest)
                                .build()));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createNewProject(
            @RequestBody(required = true) CreateNewProjectDto newProjectDto) {
        return new ResponseEntity<>(projectService.createNewProject(newProjectDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@RequestParam long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDto>> searchProjects(@RequestParam String query) {
        List<ProjectDto> projects = projectService.search(query);
        return ResponseEntity.ok(projects);
    }
}