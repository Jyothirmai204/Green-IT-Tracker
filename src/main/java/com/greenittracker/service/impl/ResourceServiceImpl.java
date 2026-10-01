package com.greenittracker.service.impl;

import com.greenittracker.dto.request.ResourceRequestDto;
import com.greenittracker.dto.request.UpdateResourceRequestDto;
import com.greenittracker.dto.response.ResourceResponseDto;
import com.greenittracker.entity.Resource;
import com.greenittracker.exception.DuplicateResourceException;
import com.greenittracker.exception.ResourceNotFoundException;
import com.greenittracker.mapper.ResourceMapper;
import com.greenittracker.repository.ResourceRepository;
import com.greenittracker.service.AuditLogService;
import com.greenittracker.service.ResourceService;
import com.greenittracker.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResourceServiceImpl
        implements ResourceService {

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;

    private final AuditLogService auditLogService;

    @Override
    public ResourceResponseDto createResource(
            ResourceRequestDto requestDto) {

        if (resourceRepository.existsByResourceName(
                requestDto.getResourceName())) {

            throw new DuplicateResourceException(
                    "Resource already exists with name : "
                            + requestDto.getResourceName()
            );
        }

        Resource resource =
                resourceMapper.toEntity(requestDto);

        Resource savedResource =
                resourceRepository.save(resource);

        auditLogService.saveAuditLog(
                SecurityUtil.getLoggedInUser(),
                "CREATE",
                "RESOURCE",
                "Resource Created : "
                        + savedResource.getResourceName()
        );

        return resourceMapper.toResponseDto(
                savedResource
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResourceResponseDto getResourceById(
            Long resourceId) {

        Resource resource =
                resourceRepository.findById(resourceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Resource not found with id : "
                                                + resourceId
                                ));

        return resourceMapper.toResponseDto(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResourceResponseDto> getAllResources() {

        return resourceRepository.findAll()
                .stream()
                .map(resourceMapper::toResponseDto)
                .toList();
    }

    @Override
    public ResourceResponseDto updateResource(
            Long resourceId,
            UpdateResourceRequestDto requestDto) {

        Resource resource =
                resourceRepository.findById(resourceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Resource not found with id : "
                                                + resourceId
                                ));

        if (requestDto.getResourceName() != null) {
            resource.setResourceName(
                    requestDto.getResourceName()
            );
        }

        if (requestDto.getStatus() != null) {
            resource.setStatus(
                    requestDto.getStatus()
            );
        }

        if (requestDto.getOwnerName() != null) {
            resource.setOwnerName(
                    requestDto.getOwnerName()
            );
        }

        if (requestDto.getLocation() != null) {
            resource.setLocation(
                    requestDto.getLocation()
            );
        }

        Resource updatedResource =
                resourceRepository.save(resource);

        auditLogService.saveAuditLog(
                SecurityUtil.getLoggedInUser(),
                "UPDATE",
                "RESOURCE",
                "Updated Resource Id : "
                        + resourceId
        );

        return resourceMapper.toResponseDto(
                updatedResource
        );
    }

    @Override
    public void deleteResource(Long resourceId) {

        Resource resource =
                resourceRepository.findById(resourceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Resource not found with id : "
                                                + resourceId
                                ));

        resourceRepository.delete(resource);

        auditLogService.saveAuditLog(
                SecurityUtil.getLoggedInUser(),
                "DELETE",
                "RESOURCE",
                "Deleted Resource Id : "
                        + resourceId
        );
    }
}