package com.greenittracker.mapper;

import com.greenittracker.dto.request.ResourceRequestDto;
import com.greenittracker.dto.response.ResourceResponseDto;
import com.greenittracker.entity.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    public Resource toEntity(ResourceRequestDto dto) {

        return Resource.builder()
                .resourceName(dto.getResourceName())
                .resourceType(dto.getResourceType())
                .status(dto.getStatus())
                .ownerName(dto.getOwnerName())
                .location(dto.getLocation())
                .build();
    }

    public ResourceResponseDto toResponseDto(Resource resource) {

        return ResourceResponseDto.builder()
                .id(resource.getId())
                .resourceName(resource.getResourceName())
                .resourceType(resource.getResourceType())
                .status(resource.getStatus())
                .ownerName(resource.getOwnerName())
                .location(resource.getLocation())
                .build();
    }
}