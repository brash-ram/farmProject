package com.rsreu.bestProject.dto.mark;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMarkDTO {
    @NotNull
    private Integer newMark;

    @NotNull
    private Integer oldMark;

    @NotNull
    private Long targetId;
}
