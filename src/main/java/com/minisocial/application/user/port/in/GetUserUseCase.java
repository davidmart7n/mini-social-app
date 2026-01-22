package com.minisocial.application.user.port.in;

import com.minisocial.application.user.dto.UserResponseDTO;

public interface GetUserUseCase {
    UserResponseDTO getUser(Long id);
}
