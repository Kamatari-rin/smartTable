package com.example.smart_table.exception;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException(long projectId) {
        super(String.format("Project with ID %d not found", projectId));
    }
}
