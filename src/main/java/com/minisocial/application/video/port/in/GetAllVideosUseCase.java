package com.minisocial.application.video.port.in;

import java.util.List;

import com.minisocial.application.video.dto.VideoResponseDTO;

public interface GetAllVideosUseCase {
    
    public List<VideoResponseDTO> getAllVideos();
}
