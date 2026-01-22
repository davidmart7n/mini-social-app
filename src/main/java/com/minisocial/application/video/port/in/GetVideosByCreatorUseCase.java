package com.minisocial.application.video.port.in;

import com.minisocial.application.video.dto.VideoResponseDTO;
import java.util.List;

public interface GetVideosByCreatorUseCase {
    List<VideoResponseDTO> getVideosByCreator(Long creatorId);
}
