package com.minisocial.application.creator.port.in;

import com.minisocial.application.creator.dto.CreatorRequestDTO;
import com.minisocial.application.creator.dto.CreatorResponseDTO;
import com.minisocial.domain.Creator;

public interface CreateCreatorUseCase {
    
    public CreatorResponseDTO createCreator(CreatorRequestDTO request);
}
