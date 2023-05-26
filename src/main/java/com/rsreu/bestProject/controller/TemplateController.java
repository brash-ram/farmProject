package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.request.TemplateDTORequest;
import com.rsreu.bestProject.dto.template.response.TemplateDTOResponse;
import com.rsreu.bestProject.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("/add")
    public ResponseEntity<TemplateDTOResponse> add(@RequestBody TemplateDTORequest template) {
        templateService.add(template);
        return ResponseEntity
                .ok()
                .body(new TemplateDTOResponse());
    }

    @PostMapping("/delete")
    public ResponseEntity<TemplateDTOResponse> delete(@RequestParam Long id) {
        if(templateService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @PutMapping("/update")
    public ResponseEntity<TemplateDTO> update(@RequestBody TemplateDTORequest request) {
        return ResponseEntity.ok(templateService.update(request));
    }

}
