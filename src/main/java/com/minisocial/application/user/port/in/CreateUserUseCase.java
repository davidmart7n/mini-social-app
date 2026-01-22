package com.minisocial.application.user.port.in;

import com.minisocial.application.user.dto.UserRequestDTO;
import com.minisocial.application.user.dto.UserResponseDTO;

public interface CreateUserUseCase {
    UserResponseDTO createUser(UserRequestDTO dto);
}
