package com.greenittracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDto {

    private Long totalResources;

    private Long activeResources;

    private Long idleResources;

    private Long recommendationsCount;

    private Double energySaved;

    private Double carbonSaved;

    private Double costSaved;

    private Integer greenScore;
}