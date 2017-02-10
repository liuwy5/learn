package com.learning.common.enums;

/**
 *
 * Created by liuw on 17-2-10.
 */
public enum LoginActionEnum {
    NORMAL(Integer.valueOf(0), "进行鉴权"),
    SKIP(Integer.valueOf(1), "跳过鉴权");

    private Integer code;
    private String mean;

    private LoginActionEnum(Integer code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMean() {
        return this.mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public static LoginActionEnum valueOfCode(Integer code) {
        for (LoginActionEnum loginActionEnum : values()) {
            if (loginActionEnum.getCode().equals(code)) {
                return loginActionEnum;
            }
        }
        return null;
    }
}
