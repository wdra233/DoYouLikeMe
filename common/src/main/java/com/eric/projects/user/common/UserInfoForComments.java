package com.eric.projects.user.common;

import java.io.Serializable;

public class UserInfoForComments implements Serializable {

    private static final long serialVersionUID = -2990181938506795811L;

    // user id
    private String id;

    // user avatar
    private String avatar;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserInfoForComments(String id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserInfoForComments{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
