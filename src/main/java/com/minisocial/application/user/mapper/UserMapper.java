
package com.minisocial.application.user.mapper;

import com.minisocial.application.user.dto.UserRequestDTO;
import com.minisocial.application.user.dto.UserResponseDTO;
import com.minisocial.domain.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setBio(dto.bio());
        return user;
    }

    public static UserResponseDTO toResponse(User user) {
        var followingIds = user.getFollowingCreators()
                               .stream()
                               .map(c -> c.getId())
                               .toList();
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getBio(),
            followingIds
        );
    }
}

