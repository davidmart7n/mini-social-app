
package com.minisocial.application.user.port.in;

public interface FollowUseCase {

    void followCreator(Long userId, Long creatorId);

    void unfollowCreator(Long userId, Long creatorId);
}