package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Course;
import com.rsreu.bestProject.data.jpa.CourseRepository;
import com.rsreu.bestProject.dto.course.CourseDTO;
import com.rsreu.bestProject.dto.course.request.AddCourseDTORequest;
import com.rsreu.bestProject.dto.course.request.UpdateCourseDTORequest;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final DtoMapper dtoMapper;

    public CourseDTO add(AddCourseDTORequest request){
        Course course = new Course();
        course.setDescription(request.getHeader())
                .setHeader(request.getHeader())
                .setLink(request.getLink());
        courseRepository.save(course);
        return dtoMapper.mapCourseToDTO(course);
    }

    public boolean delete(Long id){
        Course course = courseRepository.findById(id).orElse(null);
        if(course != null){
            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    public CourseDTO update(UpdateCourseDTORequest request){
        Course course = new Course();
        course.setDescription(request.getHeader())
                .setHeader(request.getHeader())
                .setLink(request.getLink())
                .setId(request.getId());
        courseRepository.save(course);
        return dtoMapper.mapCourseToDTO(course);
    }

    public List<CourseDTO> getAll() {
        return dtoMapper.mapCoursersToDTO(courseRepository.findAll());
    }
}
