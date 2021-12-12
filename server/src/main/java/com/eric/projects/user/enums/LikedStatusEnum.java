package com.eric.projects.user.enums;

public enum LikedStatusEnum {

    LIKE(1, "like"),
    UNLIKE(0, "unlike");

    private Integer code;
    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
