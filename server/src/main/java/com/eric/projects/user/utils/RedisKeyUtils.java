package com.eric.projects.user.utils;

public class RedisKeyUtils {

    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

    /**
     * Concatenate likedUserId and likedPostId as key. Format: 222222::333333
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    public static String getLikedKey(String likedUserId, String likedPostId) {
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }


}
