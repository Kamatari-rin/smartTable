package com.example.smart_table.exception;

public class ProjectAlreadyExistsException extends AlreadyExistsException {
    public ProjectAlreadyExistsException(String projectName) {
        super("Project with this name {0} already exists", projectName);
    }
}
