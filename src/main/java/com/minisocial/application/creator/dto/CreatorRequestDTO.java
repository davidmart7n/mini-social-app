package com.minisocial.application.creator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record CreatorRequestDTO(
    @NotBlank
    @Size(min=4)
    String name,
    @Email
    @NotBlank
    String email,
    String bio
) {}