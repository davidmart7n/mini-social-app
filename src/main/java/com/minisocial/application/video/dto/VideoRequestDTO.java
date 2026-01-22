package com.minisocial.application.video.dto;

public record VideoRequestDTO(
    Long creatorId,
    String title,
    String description
) {}
