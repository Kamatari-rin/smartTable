package com.example.smart_table.project.repository;

import com.example.smart_table.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT DISTINCT project " +
            "FROM Project project " +
            "LEFT JOIN project.resources r " +
            "WHERE (:userId IS NULL OR project.pmId IN :userId) " +
            "AND (:resources IS NULL OR r.id IN :resources) " +
            "ORDER BY " +
            "CASE " +
            "WHEN :sort = 'project' THEN project.id " +
            "WHEN :sort = 'projectName' THEN project.projectName " +
            "WHEN :sort = 'lastUpdate' THEN project.lastUpdate " +
            "ELSE project.id END ASC")
    List<Project> findProjectsForPublic(
            @Param("resources") List<Long> resources,
            @Param("userId") List<Long> userId,
            @Param("sort") String sort);

    List<Project> findByProjectNameContainingIgnoreCase(String text);
}

