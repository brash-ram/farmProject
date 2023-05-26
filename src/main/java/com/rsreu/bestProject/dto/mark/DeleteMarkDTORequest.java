package com.rsreu.bestProject.dto.mark;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMarkDTORequest {
    @NotNull
    private Integer mark;

    @NotNull
    private Long targetId;
}
