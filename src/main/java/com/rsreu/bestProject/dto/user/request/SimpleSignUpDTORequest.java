package com.rsreu.bestProject.dto.user.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSignUpDTORequest {
    @Email
    private String email;
}
