package com.rsreu.bestProject.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoleDTORequest {
    @NotNull
    private List<Integer> role;

    @NotNull
    private Long id;
}
