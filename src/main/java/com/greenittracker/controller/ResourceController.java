package com.greenittracker.controller;

import com.greenittracker.dto.request.ResourceRequestDto;
import com.greenittracker.dto.request.UpdateResourceRequestDto;
import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.ResourceResponseDto;
import com.greenittracker.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<ApiResponseDto<ResourceResponseDto>> createResource(
            @Valid @RequestBody ResourceRequestDto requestDto) {

        ResourceResponseDto response =
                resourceService.createResource(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseDto.<ResourceResponseDto>builder()
                                .success(true)
                                .message("Resource created successfully")
                                .status(HttpStatus.CREATED.value())
                                .timestamp(LocalDateTime.now())
                                .data(response)
                                .build()
                );
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ApiResponseDto<ResourceResponseDto>> getResourceById(
            @PathVariable Long resourceId) {

        ResourceResponseDto response =
                resourceService.getResourceById(resourceId);

        return ResponseEntity.ok(
                ApiResponseDto.<ResourceResponseDto>builder()
                        .success(true)
                        .message("Resource fetched successfully")
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ResourceResponseDto>>> getAllResources() {

        List<ResourceResponseDto> response =
                resourceService.getAllResources();

        return ResponseEntity.ok(
                ApiResponseDto.<List<ResourceResponseDto>>builder()
                        .success(true)
                        .message("Resources fetched successfully")
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{resourceId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ApiResponseDto<ResourceResponseDto>> updateResource(
            @PathVariable Long resourceId,
            @RequestBody UpdateResourceRequestDto requestDto) {

        ResourceResponseDto response =
                resourceService.updateResource(resourceId, requestDto);

        return ResponseEntity.ok(
                ApiResponseDto.<ResourceResponseDto>builder()
                        .success(true)
                        .message("Resource updated successfully")
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{resourceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<String>> deleteResource(
            @PathVariable Long resourceId) {

        resourceService.deleteResource(resourceId);

        return ResponseEntity.ok(
                ApiResponseDto.<String>builder()
                        .success(true)
                        .message("Resource deleted successfully")
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .data("Deleted")
                        .build()
        );
    }
}