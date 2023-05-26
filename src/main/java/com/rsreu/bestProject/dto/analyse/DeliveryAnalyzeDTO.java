package com.rsreu.bestProject.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAnalyzeDTO {
    private Long deliveryDate;
    private Long count;
    private Integer unit;
    private String typeMessageName = "DeliveryAnalyzeDTO";
}
