package com.blankk.spring_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUsernameDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String username;
}
