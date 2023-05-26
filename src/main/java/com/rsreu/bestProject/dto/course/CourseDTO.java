package com.rsreu.bestProject.dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @NotNull
    private Long id;

    @NotNull
    private String link;

    @NotNull
    private String header;

    @NotNull
    @Size(max=2000)
    private String description;
}
