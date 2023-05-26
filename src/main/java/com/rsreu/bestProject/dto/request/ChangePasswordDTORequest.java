package com.rsreu.bestProject.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTORequest {
    @NotNull
    private Long id;

    @NotNull
    private String password;
}
