package com.learning.vo;

/**
 *
 * Created by liuw on 17-2-16.
 */
public class LoginRetVo {
    private Integer code;

    private String url;

    public LoginRetVo(Integer code, String url) {
        this.code = code;
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
