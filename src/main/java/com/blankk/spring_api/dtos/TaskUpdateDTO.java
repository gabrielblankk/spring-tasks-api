package com.blankk.spring_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    @NotBlank
    @Size(min = 10, max = 10)
    private String dueDate;

    @NotBlank
    @Size(min = 1, max = 20)
    private String status;
}
