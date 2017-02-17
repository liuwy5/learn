package com.learning.common.enums;

/**
 * 兴趣类型
 * Created by lw on 16-12-24.
 */
public enum InterestTypeEnum {
    INTEREST_SPORT(0, "体育"),
    INTEREST_MOVIE(1, "电影"),
    INTEREST_MUSIC(2, "音乐"),
    INTEREST_MATH(3, "数学");

    InterestTypeEnum(Integer code, String mean) {
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

    public static InterestTypeEnum valueOfCode(Integer code) {
        for (InterestTypeEnum interestTypeEnum : InterestTypeEnum.values()) {
            if (interestTypeEnum.getCode().equals(code)) {
                return interestTypeEnum;
            }
        }
        return null;
    }
}
