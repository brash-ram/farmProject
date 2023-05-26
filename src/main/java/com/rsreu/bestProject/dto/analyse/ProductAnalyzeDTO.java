package com.rsreu.bestProject.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAnalyzeDTO {
    private String category;
    private String name;
    private String position;
    private Long dateRegistration;
}
