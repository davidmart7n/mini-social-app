package com.minisocial.application.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minisocial.application.creator.port.out.CreatorRepository;
import com.minisocial.application.user.dto.UserRequestDTO;
import com.minisocial.application.user.dto.UserResponseDTO;
import com.minisocial.application.user.mapper.UserMapper;
import com.minisocial.application.user.port.in.*;
import com.minisocial.application.user.port.out.UserRepository;
import com.minisocial.domain.Creator;
import com.minisocial.domain.User;
import com.minisocial.exception.UserNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements
        CreateUserUseCase, GetUserUseCase, UpdateUserUseCase, DeleteUserUseCase, FollowUseCase,
        GetAllUsersUseCase {

    private final CreatorRepository creatorRepository;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository,
            CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setBio(dto.bio());
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    public void followCreator(Long userId, Long creatorId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Creator creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        user.getFollowingCreators().add(creator);
        userRepository.save(user);
    }

    public void unfollowCreator(Long userId, Long creatorId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Creator creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        user.getFollowingCreators().remove(creator);
        userRepository.save(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(UserMapper::toResponse)
                .toList();
    }

}
