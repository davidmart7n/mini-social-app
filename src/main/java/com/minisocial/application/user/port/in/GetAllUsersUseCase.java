package com.minisocial.application.user.port.in;

import java.util.List;

import com.minisocial.application.user.dto.UserResponseDTO;

public interface GetAllUsersUseCase {
    
    public List<UserResponseDTO> getAllUsers();
}
