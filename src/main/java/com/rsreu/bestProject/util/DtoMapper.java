package com.rsreu.bestProject.util;


import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    private final ModelMapper modelMapper;

    public UserInfoDTO mapUserInfoToDto(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = modelMapper.map(userInfo, UserInfoDTO.class);
        userInfoDTO.setRoles(userInfo.getRoles().stream().map(role -> role.getName().getId()).toList());
        return userInfoDTO;
    }
}
