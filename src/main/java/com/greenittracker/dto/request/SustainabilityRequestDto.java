package com.greenittracker.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SustainabilityRequestDto {

    @NotNull
    @Min(0)
    private Double energySaved;

    @NotNull
    @Min(0)
    private Double carbonSaved;

    @NotNull
    @Min(0)
    private Double costSaved;

    @NotNull
    @Min(0)
    private Integer greenScore;
}

