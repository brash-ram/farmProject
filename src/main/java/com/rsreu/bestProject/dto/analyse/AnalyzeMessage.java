package com.rsreu.bestProject.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeMessage <T> {
    Integer typeMessage;

    T object;
}
