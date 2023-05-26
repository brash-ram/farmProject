package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.dto.user.request.*;
import com.rsreu.bestProject.dto.user.response.TemplateDTOResponse;
import com.rsreu.bestProject.dto.user.response.UserInfoListDTOResponse;
import com.rsreu.bestProject.dto.user.response.UserNameDTOResponse;
import com.rsreu.bestProject.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("/add")
    public ResponseEntity<TemplateDTOResponse> simpleSignUp(@RequestBody TemplateDTORequest template) {
        templateService.add(new TemplateEntity());
        return ResponseEntity
                .ok()
                .body(new TemplateDTOResponse());
    }

    @PostMapping("/delete")
    public ResponseEntity<TemplateDTOResponse> signUp(@RequestBody SignUpDTORequest signUp) {
        if(templateService.delete(new TemplateEntity());
        HttpHeaders httpHeaders = templateService.getAuthorizeHeader(signUp.getEmail());
        return ResponseEntity.ok().headers(httpHeaders).body(response);
    }

    @PutMapping("/change/update")
    public ResponseEntity<UserInfoDTO> updateUser(@RequestBody UpdateUserDTORequest info) {
        return ResponseEntity.ok(templateService.updateUser(info));
    }

}
