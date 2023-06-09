package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.course.CourseDTO;
import com.rsreu.bestProject.dto.course.request.AddCourseDTORequest;
import com.rsreu.bestProject.dto.course.request.UpdateCourseDTORequest;
import com.rsreu.bestProject.dto.course.response.CourseDTOResponse;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.response.TemplateDTOResponse;
import com.rsreu.bestProject.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<CourseDTO> add(@RequestBody AddCourseDTORequest request) {
        var dto = courseService.add(request);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        if(courseService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @PostMapping("/updateCourse")
    public ResponseEntity<CourseDTO> update(@RequestBody UpdateCourseDTORequest request) {
        return ResponseEntity.ok(courseService.update(request));
    }

    @GetMapping("/allCoursers")
    public ResponseEntity<List<CourseDTO>> getAll(){
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/geCourse")
    public ResponseEntity<CourseDTO> getById(@RequestParam Long id) {
        return ResponseEntity.ok(courseService.getProductDtoById(id));
    }

}
