package com.minisocial.application.video.service;

import com.minisocial.application.video.dto.VideoRequestDTO;
import com.minisocial.application.video.dto.VideoResponseDTO;
import com.minisocial.application.video.mapper.VideoMapper;
import com.minisocial.application.video.port.in.*;
import com.minisocial.application.video.port.out.VideoRepository;
import com.minisocial.domain.Video;
import com.minisocial.domain.Creator;
import com.minisocial.exception.CreatorNotFoundException;
import com.minisocial.exception.VideoNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements
        CreateVideoUseCase, GetVideoUseCase, UpdateVideoUseCase, DeleteVideoUseCase, LikeVideoUseCase,
        GetVideosByCreatorUseCase, GetAllVideosUseCase {

    private final VideoRepository videoRepository;
    private final com.minisocial.application.creator.port.out.CreatorRepository creatorRepository;

    public VideoServiceImpl(VideoRepository videoRepository,
            com.minisocial.application.creator.port.out.CreatorRepository creatorRepository) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
    }

    @Override
    public VideoResponseDTO createVideo(VideoRequestDTO dto) {
        Creator creator = creatorRepository.findById(dto.creatorId())
                .orElseThrow(() -> new CreatorNotFoundException(dto.creatorId()));
        Video video = VideoMapper.toEntity(dto, creator);
        video = videoRepository.save(video);
        return VideoMapper.toResponse(video);
    }

    @Override
    public VideoResponseDTO getVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));
        return VideoMapper.toResponse(video);
    }

    public List<VideoResponseDTO> getAllVideos(){
        List<Video> videosList= videoRepository.findAll();
        return videosList.stream()
                         .map(VideoMapper::toResponse)
                         .toList();
    }

    @Override
    public VideoResponseDTO updateVideo(Long id, VideoRequestDTO dto) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));
        Creator creator = creatorRepository.findById(dto.creatorId())
                .orElseThrow(() -> new CreatorNotFoundException(dto.creatorId()));
        video.setCreator(creator);
        video.setTitle(dto.title());
        video.setDescription(dto.description());
        video = videoRepository.save(video);
        return VideoMapper.toResponse(video);
    }

    @Override
    public void deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new VideoNotFoundException(id);
        }
        videoRepository.deleteById(id);
    }

    @Override
    public void likeVideo(Long userId, Long videoId) {
        var video = videoRepository.findById(videoId)
                .orElseThrow(() -> new VideoNotFoundException(videoId));

        video.setLikes(video.getLikes() + 1);
        videoRepository.save(video);
    }

    @Override
    public List<VideoResponseDTO> getVideosByCreator(Long creatorId) {
        List<Video> videos = videoRepository.findByCreatorId(creatorId);
        return videos.stream()
                .map(VideoMapper::toResponse)
                .toList();
    }

}
