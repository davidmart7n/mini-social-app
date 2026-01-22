package com.minisocial.application.user.port.out;

import com.minisocial.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
