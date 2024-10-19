package com.example.smart_table.project.repository;

import com.example.smart_table.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

//    @Query("SELECT DISTINCT project " +
//            "FROM Project project " +
//            "LEFT JOIN project.resources r " +
//            "WHERE (:userId IS NULL OR project.pmId IN :userId) " +
//            "AND (:resources IS NULL OR r.id IN :resources)")
//    List<Project> findProjectsForPublic(
//            @Param("resources") List<Long> resources,
//            @Param("userId") List<Long> userId);
//
//    @Query("SELECT project " +
//            "FROM Project project " +
//            "WHERE LOWER(project.projectName) LIKE LOWER(CONCAT('%', :text, '%'))")
//    List<Project> search(@Param("text") String text);
//
//    List<Project> findAllByOrderByProjectNameAsc();
//
//    List<Project> findAllByOrderByLastUpdateDesc();

    @Query("SELECT DISTINCT project " +
            "FROM Project project " +
            "LEFT JOIN project.resources r " +
            "WHERE (:userId IS NULL OR project.pmId IN :userId) " +
            "AND (:resources IS NULL OR r.id IN :resources) " +
            "ORDER BY " +
            "CASE " +
            "WHEN :sort = 'project' THEN project.id " +  // Сортировка по ID проекта
            "WHEN :sort = 'projectName' THEN project.projectName " +  // Сортировка по названию проекта
            "WHEN :sort = 'lastUpdate' THEN project.lastUpdate " +  // Сортировка по последнему обновлению
            "ELSE project.id END ASC")  // Сортировка по умолчанию по ID проекта
    List<Project> findProjectsForPublic(
            @Param("resources") List<Long> resources,
            @Param("userId") List<Long> userId,
            @Param("sort") String sort);

    List<Project> findByProjectNameContainingIgnoreCase(String text);
}

