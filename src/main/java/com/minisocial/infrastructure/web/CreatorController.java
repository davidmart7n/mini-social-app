package com.minisocial.infrastructure.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minisocial.application.creator.dto.CreatorRequestDTO;
import com.minisocial.application.creator.dto.CreatorResponseDTO;
import com.minisocial.application.creator.port.in.CreateCreatorUseCase;
import com.minisocial.application.creator.port.in.DeleteCreatorUseCase;
import com.minisocial.application.creator.port.in.GetAllCreatorsUseCase;
import com.minisocial.application.creator.port.in.GetCreatorUseCase;
import com.minisocial.application.creator.port.in.UpdateCreatorUseCase;
import com.minisocial.application.creator.service.CreatorServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/creators")
public class CreatorController {

    private final CreateCreatorUseCase createCreator;
    private final UpdateCreatorUseCase updateCreator;
    private final DeleteCreatorUseCase deleteCreator;
    private final GetCreatorUseCase getCreator;
    private final GetAllCreatorsUseCase getAllCreators;

    public CreatorController(
            CreateCreatorUseCase createCreator,
            UpdateCreatorUseCase updateCreator,
            DeleteCreatorUseCase deleteCreator,
            GetCreatorUseCase getCreator,
            GetAllCreatorsUseCase getAllCreators) {
        this.createCreator = createCreator;
        this.updateCreator = updateCreator;
        this.deleteCreator = deleteCreator;
        this.getCreator = getCreator;
        this.getAllCreators = getAllCreators;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatorResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(getCreator.getCreator(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreatorResponseDTO>> getAllCreators() {
        return ResponseEntity.ok(getAllCreators.getAllCreators());
    }

    @PostMapping("/create")
    public ResponseEntity<CreatorResponseDTO> create(@Valid @RequestBody CreatorRequestDTO request) {
        CreatorResponseDTO created = createCreator.createCreator(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatorResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody CreatorRequestDTO request) {
        CreatorResponseDTO updated = updateCreator.updateCreator(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCreator.deleteCreator(id);
        return ResponseEntity.noContent().build();
    }
}
