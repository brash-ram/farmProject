package com.rsreu.bestProject.dto.course.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDTORequest {
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
