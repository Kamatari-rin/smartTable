package com.example.smart_table.project.mapper;

import com.example.smart_table.project.dto.CreateNewProjectDto;
import com.example.smart_table.project.dto.ProjectDto;
import com.example.smart_table.project.entity.Project;
import com.example.smart_table.project.entity.Resource;
import com.example.smart_table.project.entity.Status;
import com.example.smart_table.project.entity.enums.ProjectStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ResourcesMapper.class})
@Service
public interface ProjectMapper {

    @Mapping(target = "status", source = "status.status")
    ProjectDto toProjectDto(Project project);

    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "resources", source = "resources", qualifiedByName = "stringSetToResourceSet")
    Project toProject(CreateNewProjectDto dto);

    @Named("stringToStatus")
    default Status mapStringToStatus(String statusName) {
        if (statusName == null || statusName.isEmpty()) {
            return null;
        }
        Status status = new Status();
        status.setStatus(ProjectStatus.valueOf(statusName));
        return status;
    }

    @Named("stringSetToResourceSet")
    default Set<Resource> mapStringSetToResourceSet(Set<String> resourceNames) {
        if (resourceNames == null || resourceNames.isEmpty()) {
            return null;
        }
        return resourceNames.stream()
                .map(resourceName -> {
                    Resource resource = new Resource();
                    resource.setResource(resourceName);
                    return resource;
                })
                .collect(Collectors.toSet());
    }
}