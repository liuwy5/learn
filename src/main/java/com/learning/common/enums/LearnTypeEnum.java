package com.learning.common.enums;

/**
 * 学习类型　雅思　托福等
 * Created by liuw on 17-4-27.
 */
public enum LearnTypeEnum {
    IELTS("ielts", "雅思", 1),
    TOEFL("toefl", "托福", 2),
    NEW_CONCEPT("new_concept", "新概念", 3),
    CHILDREN("children", "少儿英语", 4),
    FATHER_GRIND("father grind", "考研", 5),
    ;

    LearnTypeEnum(String code, String mean, Integer num) {
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

    public static LearnTypeEnum valueOfCode(String code) {
        for (LearnTypeEnum learnTypeEnum : LearnTypeEnum.values()) {
            if (learnTypeEnum.getCode().equals(code)) {
                return learnTypeEnum;
            }
        }
        return null;
    }
}
