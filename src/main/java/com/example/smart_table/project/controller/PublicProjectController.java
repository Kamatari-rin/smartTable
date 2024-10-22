package com.example.smart_table.project.controller;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.dto.RequestDto;
import com.example.smart_table.project.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@Validated
public class PublicProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> getProjectsPublic(
            @RequestParam(required = false) Set<Long> resources,
            @RequestParam(required = false) Set<Long> managers,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            HttpServletRequest httpRequest) {

        sort = Optional.ofNullable(sort)
                .filter(s -> s.equals("id") || s.equals("name") || s.equals("lastUpdate"))
                .orElse("id");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<ProjectDto> projectPage = projectService.getProjectsForPublic(
                RequestDto.builder()
                        .resources(Optional.ofNullable(resources).orElse(new HashSet<>()))
                        .managers(Optional.ofNullable(managers).orElse(new HashSet<>()))
                        .pagination(pageable)
                        .request(httpRequest)
                        .build());

        return projectPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(projectPage);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createNewProject(
            @Valid @RequestBody CreateNewProjectDto newProjectDto) {
        return new ResponseEntity<>(projectService.createNewProject(newProjectDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@RequestParam long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProjectDto>> searchProjects(
            @RequestParam String query,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<ProjectDto> projects = projectService.search(query, pageable);
        return ResponseEntity.ok(projects);
    }
}