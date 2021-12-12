package com.eric.projects.user.service;

import com.eric.projects.user.dataobject.UserLike;
import com.eric.projects.user.dto.LikedCountDTO;

import java.util.List;

public interface RedisService {

    /**
     * like, status is 1
     * @param likedUserId
     * @param likedPostId
     */
    void saveLiked2Redis(String likedUserId, String likedPostId);

    /**
     * unlike, status to 0
     * @param likedUserId
     * @param likedPostId
     */
    void unlikeFromRedis(String likedUserId, String likedPostId);

    /**
     * delete one liked from Redis
     * @param likedUserId
     * @param likedPostId
     */
    void deleteLikedFromRedis(String likedUserId, String likedPostId);

    /**
     * increment 1 like
     * @param likedUserId
     */
    void incrementLikedCount(String likedUserId);

    /**
     * decrement 1 like
     * @param likedUserId
     */
    void decrementLikedCount(String likedUserId);


    /**
     * Get all liked data from redis
     *
     * @return
     */
    List<UserLike> getLikedDataFromRedis();

    /**
     * get all liked counts from redis
     *
     * @return
     */
    List<LikedCountDTO> getLikedCountFromRedis();

}
