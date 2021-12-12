package com.eric.projects.user.dto;

import java.io.Serializable;

public class LikedCountDTO implements Serializable {

    private static final long serialVersionUID = -2856160546081194945L;

    private String id;

    private Integer count;

    public LikedCountDTO() {
    }

    public LikedCountDTO(String id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
