package com.minisocial.application.user.port.in;

import com.minisocial.application.user.dto.UserRequestDTO;
import com.minisocial.application.user.dto.UserResponseDTO;

public interface UpdateUserUseCase {
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
}
