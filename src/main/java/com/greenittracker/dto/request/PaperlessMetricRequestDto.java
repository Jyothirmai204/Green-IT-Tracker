package com.greenittracker.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperlessMetricRequestDto {

    @NotNull
    @Min(0)
    private Integer printedPages;

    @NotNull
    @Min(0)
    private Integer digitalDocuments;

    @NotNull
    @Min(0)
    private Integer paperSaved;
}