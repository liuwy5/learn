package com.learning.common.enums;

/**
 * 学习难度
 * Created by liuw on 17-4-27.
 */
public enum LearnLevelEnum {
    SIMPLE("simple", "简单", 1),
    MEDIUM("medium", "中等", 2),
    DIFFICULT("difficult", "困难", 3),
    ;

    LearnLevelEnum(String code, String mean, Integer num) {
        this.code = code;
        this.mean = mean;
        this.num = num;
    }

    private String code;
    private String mean;
    private Integer num;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public static LearnLevelEnum valueOfCode(String code) {
        for (LearnLevelEnum LearnLevelEnum : LearnLevelEnum.values()) {
            if (LearnLevelEnum.getCode().equals(code)) {
                return LearnLevelEnum;
            }
        }
        return null;
    }
}
