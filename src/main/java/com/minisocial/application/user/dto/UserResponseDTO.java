package com.minisocial.application.user.dto;

import java.util.List;

public record UserResponseDTO(
    Long id,
    String username,
    String email,
    String bio,
    List<Long> followingCreatorIds
) {}
