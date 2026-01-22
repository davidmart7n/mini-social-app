package com.minisocial.application.creator.port.in;

import com.minisocial.application.creator.dto.CreatorResponseDTO;

public interface GetCreatorUseCase {
    
    public CreatorResponseDTO getCreator(Long id);
}
