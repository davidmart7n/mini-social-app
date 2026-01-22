package com.minisocial.application.video.port.out;

import com.minisocial.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByCreatorId(Long creatorId);
}
