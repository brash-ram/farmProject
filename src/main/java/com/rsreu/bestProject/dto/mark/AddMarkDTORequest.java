package com.rsreu.bestProject.dto.mark;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMarkDTORequest {
    @NotNull
    private Integer mark;

    @NotNull
    private Long targetId;
}
