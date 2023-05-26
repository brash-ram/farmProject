package com.rsreu.bestProject.dto.user.response;

import com.rsreu.bestProject.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoListDTOResponse {
    @NotNull
    private List<UserInfoDTO> usersList;
}
