package com.rsreu.bestProject.dto.user.request;

import com.rsreu.bestProject.enums.RoleEnum;
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
    private List<RoleEnum> role;

    @NotNull
    private Long id;
}
