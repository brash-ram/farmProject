package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.mark.AddMarkDTORequest;
import com.rsreu.bestProject.dto.mark.ChangeMarkDTO;
import com.rsreu.bestProject.dto.mark.DeleteMarkDTORequest;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.dto.user.request.*;
import com.rsreu.bestProject.dto.user.response.UserInfoListDTOResponse;
import com.rsreu.bestProject.security.AuthUtil;
import com.rsreu.bestProject.service.MarkService;
import com.rsreu.bestProject.service.UserService;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final DtoMapper dtoMapper;

    private final MarkService markService;

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

    @PostMapping("/search/email")
    public ResponseEntity<UserInfoDTO> signUp(@RequestParam String email) {

        return ResponseEntity.ok().body(userService.searchByEmail(email));
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserInfoDTO> signUp(@RequestBody SignUpDTORequest signUp) {
        UserInfoDTO response = userService.signUp(signUp);
        HttpHeaders httpHeaders = userService.getAuthorizeHeader(signUp.getEmail());
        return ResponseEntity.ok().headers(httpHeaders).body(response);
    }

    @PostMapping("/change/update")
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
    public ResponseEntity<UserInfoDTO> getCurrent(){
        UserInfo user = AuthUtil.getUserFromContext();
        Double aDouble = markService.getRating(user);
        return ResponseEntity.ok(dtoMapper.mapUserInfoToDto(user, aDouble));
    }

    @PostMapping("/change/password")
    public ResponseEntity<UserInfoDTO> changePassword(@RequestBody ChangePasswordDTORequest dto) {
        return ResponseEntity.ok(userService.updatePassword(dto));
    }

    @PostMapping("/change/role")
    public ResponseEntity<UserInfoDTO> updateRole(@RequestBody ChangeRoleDTORequest changeRoleDTORequest) {
        return ResponseEntity.ok(userService.updateRoles(changeRoleDTORequest));
    }

    @PostMapping("/change/role/byId")
    public ResponseEntity<UserInfoDTO> updateRole(@RequestParam Long id) {
        UserInfo user = userService.getById(id);
        Double rating = markService.getRating(user);
        return ResponseEntity.ok(dtoMapper.mapUserInfoToDto(user, rating));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId){
        if(userService.deleteUser(userId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/byId")
    public ResponseEntity<UserInfoDTO> getUser(@RequestParam Long id) {
        UserInfo user = userService.getById(id);
        Double rating = markService.getRating(user);
        return ResponseEntity.ok(dtoMapper.mapUserInfoToDto(user, rating));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<UserInfoDTO>> addAll(@RequestBody List<SignUpCustomDTORequest> requests) {
        return ResponseEntity.ok(userService.addAll(requests));
    }

}
