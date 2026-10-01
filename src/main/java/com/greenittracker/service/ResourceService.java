package com.greenittracker.service;

import com.greenittracker.dto.request.ResourceRequestDto;
import com.greenittracker.dto.request.UpdateResourceRequestDto;
import com.greenittracker.dto.response.ResourceResponseDto;

import java.util.List;

public interface ResourceService {

    ResourceResponseDto createResource(
            ResourceRequestDto requestDto);

    ResourceResponseDto getResourceById(
            Long resourceId);

    List<ResourceResponseDto> getAllResources();

    ResourceResponseDto updateResource(
            Long resourceId,
            UpdateResourceRequestDto requestDto);

    void deleteResource(
            Long resourceId);
}