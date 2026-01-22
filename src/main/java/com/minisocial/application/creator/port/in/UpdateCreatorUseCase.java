package com.minisocial.application.creator.port.in;

import com.minisocial.application.creator.dto.CreatorRequestDTO;
import com.minisocial.application.creator.dto.CreatorResponseDTO;

public interface UpdateCreatorUseCase {
    
    public CreatorResponseDTO updateCreator(Long id, CreatorRequestDTO request);
}
