package com.lwj.constant;

public enum MessageEnum {
    PASSWORD_ERROR("密码错误", "10001"),
    ACCOUNT_NOT_FOUND("账号不存在", "10002"),
    ACCOUNT_LOCKED("账号被锁定", "10003"),
    ALREADY_EXISTS("已存在", "10004"),
    UNKNOWN_ERROR("未知错误", "10005"),
    USER_NOT_LOGIN("用户未登录", "10006"),
    CATEGORY_BE_RELATED_BY_SETMEAL("当前分类关联了套餐,不能删除", "10007");

    private String message;
    private String code;
    MessageEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
