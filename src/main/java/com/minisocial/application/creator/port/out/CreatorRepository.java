package com.minisocial.application.creator.port.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minisocial.domain.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator,Long> {

}
