package com.miniservice.constant;

public enum RegisterResultEnum {
    /**
     * 注册成功
     */
    OK("Register successfully."),
    /**
     * 注册失败
     */
    FAILED("Phone number is already registered.");

    private final String message;

    RegisterResultEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
