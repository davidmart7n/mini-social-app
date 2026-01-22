package com.minisocial.application.creator.mapper;

import com.minisocial.application.creator.dto.CreatorRequestDTO;
import com.minisocial.application.creator.dto.CreatorResponseDTO;
import com.minisocial.domain.Creator;

public class CreatorMapper {

    public static Creator toEntity(CreatorRequestDTO request ){

        return new Creator(null, request.name(), request.email(), request.bio());
    }

    public static CreatorResponseDTO toResponse(Creator creator) {

        return new CreatorResponseDTO(creator.getId(),
                                        creator.getName(),
                                        creator.getEmail(),
                                        creator.getBio());
    }
}