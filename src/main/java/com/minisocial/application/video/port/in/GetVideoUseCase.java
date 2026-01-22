package com.minisocial.application.video.port.in;

import com.minisocial.application.video.dto.VideoResponseDTO;

public interface GetVideoUseCase {
    VideoResponseDTO getVideo(Long id);
}
