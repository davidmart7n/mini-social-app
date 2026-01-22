package com.minisocial.application.video.mapper;

import com.minisocial.application.video.dto.VideoRequestDTO;
import com.minisocial.application.video.dto.VideoResponseDTO;
import com.minisocial.domain.Video;
import com.minisocial.domain.Creator;

public class VideoMapper {

    public static Video toEntity(VideoRequestDTO dto, Creator creator) {
        Video video = new Video();
        video.setCreator(creator);
        video.setTitle(dto.title());
        video.setDescription(dto.description());
        video.setLikes(0);
        return video;
    }

    public static VideoResponseDTO toResponse(Video video) {
        return new VideoResponseDTO(
            video.getId(),
            video.getCreator().getId(),
            video.getTitle(),
            video.getDescription(),
            video.getLikes()
        );
    }
}
