package com.rsreu.bestProject.controller;


import com.rsreu.bestProject.dto.user.request.SimpleSignUpDTORequest;
import com.rsreu.bestProject.dto.template.response.TemplateDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

//    @PostMapping("/add")
//    public ResponseEntity<TemplateDTOResponse> simpleSignUp(@RequestBody SimpleSignUpDTORequest simpleSignUpDTORequest) {
//        userService.simpleSignUp(simpleSignUpDTORequest.getEmail());
//        return ResponseEntity
//                .ok()
//                .body(new TemplateDTOResponse());
//    }
}
