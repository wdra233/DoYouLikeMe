package com.eric.projects.user.service.impl;

import com.eric.projects.user.dataobject.UserLike;
import com.eric.projects.user.service.LikedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class LikedServiceImpl implements LikedService {
    @Override
    public UserLike save(UserLike userLike) {
        return null;
    }

    @Override
    public List<UserLike> saveAll(List<UserLike> list) {
        return null;
    }

    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
        return null;
    }

    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return null;
    }

    @Override
    public void transLikedFromRedis2DB() {

    }

    @Override
    public void transLikedCountFromRedis2DB() {

    }
}
