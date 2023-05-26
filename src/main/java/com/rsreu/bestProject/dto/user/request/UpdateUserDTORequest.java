package com.rsreu.bestProject.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTORequest {
    @NotNull
    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String bio;

    @Email
    private String email;
}
