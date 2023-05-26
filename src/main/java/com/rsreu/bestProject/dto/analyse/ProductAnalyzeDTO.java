package com.rsreu.bestProject.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductAnalyzeDTO {
    private String category;
    private String name;
    private String position;
    private Long dateRegistration;
    private String typeMessageName = "ProductAnalyzeDTO";
}
