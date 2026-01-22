package com.minisocial.infrastructure.web;

import com.minisocial.application.video.dto.VideoRequestDTO;
import com.minisocial.application.video.dto.VideoResponseDTO;
import com.minisocial.application.video.port.in.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final CreateVideoUseCase createVideo;
    private final GetVideoUseCase getVideo;
    private final UpdateVideoUseCase updateVideo;
    private final DeleteVideoUseCase deleteVideo;
    private final LikeVideoUseCase likeVideo;
    private final GetVideosByCreatorUseCase getVideosByCreator;
    private final GetAllVideosUseCase getAllVideos;

    public VideoController(CreateVideoUseCase createVideo,
            GetVideoUseCase getVideo,
            UpdateVideoUseCase updateVideo,
            DeleteVideoUseCase deleteVideo,
            LikeVideoUseCase likeVideo,
            GetVideosByCreatorUseCase getVideosByCreator,
            GetAllVideosUseCase getAllVideos
            ) {
        this.createVideo = createVideo;
        this.getVideo = getVideo;
        this.updateVideo = updateVideo;
        this.deleteVideo = deleteVideo;
        this.likeVideo = likeVideo;
        this.getVideosByCreator = getVideosByCreator;
        this.getAllVideos=getAllVideos;
    }

    @PostMapping("/create")
    public ResponseEntity<VideoResponseDTO> create(@RequestBody VideoRequestDTO dto) {
        return ResponseEntity.ok(createVideo.createVideo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(getVideo.getVideo(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoResponseDTO>> getAllVideos() {
        return ResponseEntity.ok(getAllVideos.getAllVideos());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VideoResponseDTO> update(@PathVariable Long id,
            @RequestBody VideoRequestDTO dto) {
        return ResponseEntity.ok(updateVideo.updateVideo(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteVideo.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{videoId}/like/{userId}")
    public ResponseEntity<Void> likeVideo(
            @PathVariable Long videoId,
            @PathVariable Long userId) {

        likeVideo.likeVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<VideoResponseDTO>> getVideosByCreator(
            @PathVariable Long creatorId) {
        return ResponseEntity.ok(getVideosByCreator.getVideosByCreator(creatorId));
    }

}
