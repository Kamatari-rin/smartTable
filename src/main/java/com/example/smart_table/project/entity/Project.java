package com.example.smart_table.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pm_id", nullable = false)
    private long pmId;

    @Column(name = "project_name", nullable = false, length = 255)
    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    @ToString.Exclude
    private Status status;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "project_resources",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources = new HashSet<>();

    @Column(name = "estimation")
    private long estimation;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "start_project")
    private LocalDateTime projectStart;

    @Column(name = "end_project")
    private  LocalDateTime projectEnd;
}