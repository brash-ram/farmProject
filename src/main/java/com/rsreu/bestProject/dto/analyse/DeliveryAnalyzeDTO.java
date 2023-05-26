package com.rsreu.bestProject.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeliveryAnalyzeDTO {
    private ProductAnalyzeDTO product;
    private Integer deliveryDate;
    private Long count;
    private Integer unit;
    private String typeMessageName = "DeliveryAnalyzeDTO";
}
