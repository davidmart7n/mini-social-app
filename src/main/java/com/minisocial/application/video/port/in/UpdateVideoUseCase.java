package com.minisocial.application.video.port.in;

import com.minisocial.application.video.dto.VideoRequestDTO;
import com.minisocial.application.video.dto.VideoResponseDTO;

public interface UpdateVideoUseCase {
    VideoResponseDTO updateVideo(Long id, VideoRequestDTO dto);
}
