package com.eric.projects.user.service.impl;

import com.eric.projects.user.dataobject.UserLike;
import com.eric.projects.user.enums.LikedStatusEnum;
import com.eric.projects.user.repository.UserLikeRepository;
import com.eric.projects.user.service.LikedService;
import com.eric.projects.user.service.RedisService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LikedServiceImpl implements LikedService {

    @Autowired
    private UserLikeRepository likeRepository;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public UserLike save(UserLike userLike) {
        return likeRepository.save(userLike);
    }

    @Override
    @Transactional
    public List<UserLike> saveAll(List<UserLike> list) {
        return likeRepository.saveAll(list);
    }

    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
        return likeRepository.findByLikedUserIdAndStatus(likedUserId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
        return likeRepository.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return likeRepository.findByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike userLikeRedis : list) {
            UserLike userLikeDB = getByLikedUserIdAndLikedPostId(userLikeRedis.getLikedUserId(), userLikeRedis.getLikedPostId());
            if (userLikeDB == null) {
                // no record, save
                save(userLikeRedis);
            } else {
                // update existing record
                userLikeDB.setStatus(userLikeRedis.getStatus());
                save(userLikeDB);
            }
        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {
        // TODO: needs to implement userService
    }
}
