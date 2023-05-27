package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.Mark;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.mark.AddMarkDTORequest;
import com.rsreu.bestProject.dto.mark.ChangeMarkDTO;
import com.rsreu.bestProject.dto.mark.DeleteMarkDTORequest;
import com.rsreu.bestProject.dto.mark.MarkDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.security.AuthUtil;
import com.rsreu.bestProject.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarkController {
    
    private final MarkService markService;

    @PostMapping("/add")
    public ResponseEntity<Void> addMark(@RequestBody MarkDTO dto) {
        UserInfo user = AuthUtil.getUserFromContext();
        markService.addMark(user, dto.getToId(), dto.getMark());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change/marks/change")
    public ResponseEntity<Void> updateRating(@RequestBody MarkDTO dto) {
        UserInfo user = AuthUtil.getUserFromContext();
        markService.changeMark(user,  dto.getToId(), dto.getMark());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change/marks/delete")
    public ResponseEntity<Void> deleteMark(@RequestBody MarkDTO dto) {
        UserInfo user = AuthUtil.getUserFromContext();
        markService.deleteMark(user,  dto.getToId(), dto.getMark());
        return ResponseEntity.ok().build();
    }


}
