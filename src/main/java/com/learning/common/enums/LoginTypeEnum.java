package com.learning.common.enums;

/**
 * 
 * Created by liuw on 17-2-10.
 */
public enum LoginTypeEnum {
    CUSTOMER(0, "前台"),
    ADMIN(1, "后台");

    private Integer code;
    private String mean;

    private LoginTypeEnum(Integer code, String mean) {
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

    public static LoginTypeEnum valueOfCode(Integer code) {
        for (LoginTypeEnum loginTypeEnum : values()) {
            if (loginTypeEnum.getCode().equals(code)) {
                return loginTypeEnum;
            }
        }

        return null;
    }
}
