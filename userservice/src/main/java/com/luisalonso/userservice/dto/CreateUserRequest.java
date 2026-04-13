package com.luisalonso.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

    @Email
    private String email;

    @NotBlank
    private String name;

}
