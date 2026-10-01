package com.greenittracker.entity;

import com.greenittracker.enums.ResourceStatus;
import com.greenittracker.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String resourceName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ResourceStatus status;

    @NotBlank
    private String ownerName;

    @NotBlank
    private String location;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}