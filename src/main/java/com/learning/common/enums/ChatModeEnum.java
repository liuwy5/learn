package com.learning.common.enums;

/**
 * 聊天模式
 * Created by liuw on 17-1-10.
 */
public enum ChatModeEnum {
    PRIVATE_CHAT(0, "私聊"),
    GROUP_CHAT(1, "群聊");

    ChatModeEnum(Integer code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    private Integer code;
    private String mean;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public static ChatModeEnum valueOfCode(Integer code) {
        for (ChatModeEnum chatModeEnum : ChatModeEnum.values()) {
            if (chatModeEnum.getCode().equals(code)) {
                return chatModeEnum;
            }
        }
        return null;
    }
}
