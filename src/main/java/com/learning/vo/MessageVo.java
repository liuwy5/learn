package com.learning.vo;

/**
 *
 * Created by lw on 16-12-22.
 */
public class MessageVo {
    private String sender;

    private String receiver;

    private String content;

    private boolean self;

    private Integer chatMode;

    private Integer interestId;

    private String date;

    private Integer type;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public Integer getChatMode() {
        return chatMode;
    }

    public void setChatMode(Integer chatMode) {
        this.chatMode = chatMode;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
