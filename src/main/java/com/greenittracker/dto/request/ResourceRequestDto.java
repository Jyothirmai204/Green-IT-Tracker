package com.greenittracker.dto.request;

import com.greenittracker.enums.ResourceStatus;
import com.greenittracker.enums.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequestDto {

    @NotBlank(message = "Resource name is required")
    private String resourceName;

    @NotNull(message = "Resource type is required")
    private ResourceType resourceType;

    @NotNull(message = "Status is required")
    private ResourceStatus status;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotBlank(message = "Location is required")
    private String location;
}