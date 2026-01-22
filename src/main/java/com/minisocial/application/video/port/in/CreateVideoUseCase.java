package com.minisocial.application.video.port.in;

import com.minisocial.application.video.dto.VideoRequestDTO;
import com.minisocial.application.video.dto.VideoResponseDTO;

public interface CreateVideoUseCase {
    VideoResponseDTO createVideo(VideoRequestDTO dto);
}
