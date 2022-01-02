package com.eric.projects.user.service.impl;

import com.eric.projects.user.dataobject.UserLike;
import com.eric.projects.user.dto.LikedCountDTO;
import com.eric.projects.user.enums.LikedStatusEnum;
import com.eric.projects.user.service.LikedService;
import com.eric.projects.user.service.RedisService;
import com.eric.projects.user.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LikedService likedService;

    @Override
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);
    }

    @Override
    public void decrementLikedCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, -1);
    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while(cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String)entry.getKey();
            String[] split = key.split("::");
            String likedUserId = split[0];
            String likedPostId = split[1];
            Integer value = (Integer) entry.getValue();

            UserLike userLike = new UserLike(likedUserId, likedPostId, value);
            list.add(userLike);

            // delete from redis after adding to list
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }
        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while(cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String)entry.getKey();
            Integer value = (Integer) entry.getValue();
            LikedCountDTO dto = new LikedCountDTO(key, value);

            list.add(dto);
            // delete from redis after adding to list
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }
}
