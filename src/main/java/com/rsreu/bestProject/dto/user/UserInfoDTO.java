package com.rsreu.bestProject.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    @NotNull
    private String fullName;

    @NotNull
    private String bio;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long id;

    @NotNull
    private List<Integer> roles;

    @NotNull
    private Long dateRegistration;
}
