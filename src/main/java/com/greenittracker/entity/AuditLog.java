package com.greenittracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String action;

    @NotBlank
    private String module;

    @Column(length = 1000)
    private String description;

    private LocalDateTime actionTime;

    @PrePersist
    public void prePersist() {
        actionTime = LocalDateTime.now();
    }
}