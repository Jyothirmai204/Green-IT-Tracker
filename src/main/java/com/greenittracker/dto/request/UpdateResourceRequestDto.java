package com.greenittracker.dto.request;

import com.greenittracker.enums.ResourceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateResourceRequestDto {

    private String resourceName;

    private ResourceStatus status;

    private String ownerName;

    private String location;
}