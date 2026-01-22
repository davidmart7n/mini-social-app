package com.minisocial.application.creator.service;

import java.util.List;

import org.hibernate.persister.entity.mutation.UpdateCoordinator;
import org.springframework.stereotype.Service;

import com.minisocial.application.creator.dto.CreatorRequestDTO;
import com.minisocial.application.creator.dto.CreatorResponseDTO;
import com.minisocial.application.creator.mapper.CreatorMapper;
import com.minisocial.application.creator.port.in.CreateCreatorUseCase;
import com.minisocial.application.creator.port.in.DeleteCreatorUseCase;
import com.minisocial.application.creator.port.in.GetAllCreatorsUseCase;
import com.minisocial.application.creator.port.in.GetCreatorUseCase;
import com.minisocial.application.creator.port.in.UpdateCreatorUseCase;
import com.minisocial.application.creator.port.out.CreatorRepository;
import com.minisocial.domain.Creator;
import com.minisocial.exception.CreatorNotFoundException;

@Service
public class CreatorServiceImpl implements CreateCreatorUseCase,
        DeleteCreatorUseCase,
        GetCreatorUseCase,
        UpdateCreatorUseCase,
        GetAllCreatorsUseCase {

    CreatorRepository repository;

    public CreatorServiceImpl(CreatorRepository repository) {
        this.repository = repository;

    }

    public CreatorResponseDTO getCreator(Long id) {

        Creator creator = repository.findById(id)
                .orElseThrow(() -> new CreatorNotFoundException(id));
        return CreatorMapper.toResponse(creator);
    }

    public List<CreatorResponseDTO> getAllCreators() {

        List<Creator> creatorsList = repository.findAll();
        return creatorsList.stream()
                .map(CreatorMapper::toResponse)
                .toList();
    }

    @Override
    public CreatorResponseDTO createCreator(CreatorRequestDTO request) {
        Creator requestedCreator = CreatorMapper.toEntity(request);
        Creator newCreator = repository.save(requestedCreator);
        return CreatorMapper.toResponse(newCreator);
    }

    @Override
    public CreatorResponseDTO updateCreator(Long id, CreatorRequestDTO request) {
        Creator existingCreator = repository.findById(id)
                .orElseThrow(() -> new CreatorNotFoundException(id));

        // Actualizamos solo los campos necesarios
        existingCreator.setName(request.name());
        existingCreator.setEmail(request.email());
        existingCreator.setBio(request.bio());

        Creator updated = repository.save(existingCreator);
        return CreatorMapper.toResponse(updated);
    }

    @Override
    public void deleteCreator(Long id) {
        if (!repository.existsById(id)) {
            throw new CreatorNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
