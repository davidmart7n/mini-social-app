package com.minisocial.infrastructure.web;

import com.minisocial.application.user.dto.UserRequestDTO;
import com.minisocial.application.user.dto.UserResponseDTO;
import com.minisocial.application.user.port.in.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUser;
    private final GetUserUseCase getUser;
    private final UpdateUserUseCase updateUser;
    private final DeleteUserUseCase deleteUser;
    private final FollowUseCase follow;
    private final GetAllUsersUseCase getAllUsers;

    public UserController(
            CreateUserUseCase createUser,
            GetUserUseCase getUser,
            UpdateUserUseCase updateUser,
            DeleteUserUseCase deleteUser,
            FollowUseCase follow,
            GetAllUsersUseCase getAllUsers
            ) {
        this.createUser = createUser;
        this.getUser = getUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.follow=follow;
        this.getAllUsers=getAllUsers;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(createUser.createUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(getUser.getUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(getAllUsers.getAllUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(updateUser.updateUser(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUser.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/follow/{creatorId}")
    public ResponseEntity<Void> follow(
            @PathVariable Long userId,
            @PathVariable Long creatorId) {
        follow.followCreator(userId, creatorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/unfollow/{creatorId}")
    public ResponseEntity<Void> unfollow(
            @PathVariable Long userId,
            @PathVariable Long creatorId) {
        follow.unfollowCreator(userId, creatorId);
        return ResponseEntity.ok().build();
    }

}
