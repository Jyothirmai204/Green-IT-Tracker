package com.greenittracker.dto.response;

import com.greenittracker.enums.ResourceStatus;
import com.greenittracker.enums.ResourceType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceResponseDto {

    private Long id;

    private String resourceName;

    private ResourceType resourceType;

    private ResourceStatus status;

    private String ownerName;

    private String location;
};
