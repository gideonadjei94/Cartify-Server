package com.BeeTech.Cartify.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "User firstname is required")
    private String firstName;

    @NotBlank(message = "User lastname")
    private String lastName;

    @NotBlank(message = "User email is required")
    @Email(message = "Enter a valid email")
    private String email;

    private String password;
}
