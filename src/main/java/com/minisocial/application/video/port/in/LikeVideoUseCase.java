package com.minisocial.application.video.port.in;

public interface LikeVideoUseCase {
    void likeVideo(Long userId, Long videoId);
}
