package com.rsreu.bestProject.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTORequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
