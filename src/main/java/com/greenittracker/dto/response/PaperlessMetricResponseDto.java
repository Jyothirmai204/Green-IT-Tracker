package com.greenittracker.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperlessMetricResponseDto {

    private Integer printedPages;

    private Integer digitalDocuments;

    private Integer paperSaved;
}