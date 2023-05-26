package com.rsreu.bestProject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTORequest {
    @NotNull
    private String fullName;

    @NotNull
    private String bio;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String code;

    @NotNull
    private String password;
}
