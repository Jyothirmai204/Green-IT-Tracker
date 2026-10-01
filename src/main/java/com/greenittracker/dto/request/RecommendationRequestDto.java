package com.greenittracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationRequestDto {

    @NotNull
    private Long resourceId;

    @NotBlank
    private String issue;

    @NotBlank
    private String recommendation;

    @NotNull
    private Double estimatedSavings;
}