package com.example.smart_table.project.mapper;

import com.example.smart_table.project.dto.ResourceDto;
import com.example.smart_table.project.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourcesMapper {

    @Mapping(target = "resource", source = "resource")
    ResourceDto resourceToString(Resource resource);
}
