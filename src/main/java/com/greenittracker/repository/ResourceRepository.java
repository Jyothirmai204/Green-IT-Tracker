package com.greenittracker.repository;

import com.greenittracker.entity.Resource;
import com.greenittracker.enums.ResourceStatus;
import com.greenittracker.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    boolean existsByResourceName(String resourceName);

    List<Resource> findByStatus(ResourceStatus status);

    List<Resource> findByResourceType(ResourceType resourceType);
}