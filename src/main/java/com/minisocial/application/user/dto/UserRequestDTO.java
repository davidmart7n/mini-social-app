package com.minisocial.application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotBlank
    @Size(min=4)
    String username,
    @Email
    @NotBlank
    String email,
    String bio
) {}
