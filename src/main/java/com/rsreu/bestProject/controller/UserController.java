package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.dto.user.request.*;
import com.rsreu.bestProject.dto.user.response.UserInfoListDTOResponse;
import com.rsreu.bestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp/sendEmail")
    public ResponseEntity<Boolean> simpleSignUp(@RequestBody SendEmailDTORequest sendEmailDTORequest) {
        userService.simpleSignUp(sendEmailDTORequest.getEmail());
        return ResponseEntity
                .ok()
                .body(true);
    }

//    @PostMapping("/signUp/confirmEmail")
//    public ResponseEntity<Void> confirmEmail(@RequestBody ConfirmEmailDTORequest request) {
//        boolean confirm = userService.confirmEmail(request.getEmail(), request.getCode());
//        if (confirm) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/signUp")
    public ResponseEntity<UserInfoDTO> signUp(@RequestBody SignUpDTORequest signUp) {
        UserInfoDTO response = userService.signUp(signUp);
        HttpHeaders httpHeaders = userService.getAuthorizeHeader(signUp.getEmail());
        return ResponseEntity.ok().headers(httpHeaders).body(response);
    }

    @PutMapping("/change/update")
    public ResponseEntity<UserInfoDTO> updateUser(@RequestBody UpdateUserDTORequest info) {
        return ResponseEntity.ok(userService.updateUser(info));
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserInfoDTO> signIn(@RequestBody SignInDTORequest request) {
        UserInfoDTO dto = userService.signIn(request.getEmail(), request.getPassword());
        if (dto != null) {
            HttpHeaders httpHeaders = userService.getAuthorizeHeader(request.getEmail());
            return ResponseEntity.ok().headers(httpHeaders).body(dto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<UserInfoListDTOResponse> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/self")
    public ResponseEntity<UserInfoDTO> getCurrent(HttpServletRequest request){
      return ResponseEntity.ofNullable(userService.getUserInfo(request.getUserPrincipal().getName()));
    }

    @PutMapping("/change/password")
    public ResponseEntity<UserInfoDTO> changePassword(@RequestBody ChangePasswordDTORequest dto) {
        return ResponseEntity.ok(userService.updatePassword(dto));
    }

    @PutMapping("/change/role")
    public ResponseEntity<UserInfoDTO> updateRole(@RequestBody ChangeRoleDTORequest changeRoleDTORequest) {
        return ResponseEntity.ok(userService.updateRoles(changeRoleDTORequest));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId){
        if(userService.deleteUser(userId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
