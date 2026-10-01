package com.greenittracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SustainabilityResponseDto {

    private Double energySaved;

    private Double carbonSaved;

    private Double costSaved;

    private Integer greenScore;
}