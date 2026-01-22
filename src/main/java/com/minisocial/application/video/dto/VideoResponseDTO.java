package com.minisocial.application.video.dto;

public record VideoResponseDTO(
    Long id,
    Long creatorId,
    String title,
    String description,
    int likes
) {}
