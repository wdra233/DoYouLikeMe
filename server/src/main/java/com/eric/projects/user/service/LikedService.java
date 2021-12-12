package com.eric.projects.user.service;

import com.eric.projects.user.dataobject.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikedService {
    /**
     * Save liked record
     * @param userLike
     * @return
     */
    UserLike save(UserLike userLike);


    /**
     * Batch save list
     * @param list
     * @return
     */
    List<UserLike> saveAll(List<UserLike> list);


    /**
     * Retrieve likedlist by userId
     * @param likedUserId
     * @param pageable
     * @return
     */
    Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);


    Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable);


    UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);


    /**
     * transfer liked data from redis to db
     */
    void transLikedFromRedis2DB();

    /**
     * transfer liked counts from redis to db
     */
    void transLikedCountFromRedis2DB();

}
