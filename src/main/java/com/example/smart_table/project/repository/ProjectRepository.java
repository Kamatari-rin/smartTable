package com.example.smart_table.project.repository;

import com.example.smart_table.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT DISTINCT project " +
            "FROM Project project " +
            "LEFT JOIN FETCH project.resources r " +
            "WHERE (:userId IS NULL OR project.pmId IN :userId) " +
            "AND (:resources IS NULL OR r.id IN :resources)")
    Page<Project> findProjectsForPublic(
            @Param("resources") Set<Long> resources,
            @Param("userId") Set<Long> userId,
            Pageable pageable);


    Page<Project> findByProjectNameContainingIgnoreCase(String query, Pageable pageable);

    Optional<Project> findByProjectName(String projectName);
}

