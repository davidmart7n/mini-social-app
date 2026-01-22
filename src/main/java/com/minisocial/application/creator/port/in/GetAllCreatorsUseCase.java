package com.minisocial.application.creator.port.in;

import java.util.List;

import com.minisocial.application.creator.dto.CreatorResponseDTO;

public interface GetAllCreatorsUseCase {
    
    public List<CreatorResponseDTO> getAllCreators();
}
