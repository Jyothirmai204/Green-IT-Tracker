package com.greenittracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponseDto {

    private Long id;

    private String issue;

    private String recommendation;

    private Double estimatedSavings;

    private String priority;
}