package com.learning.vo;

/**
 *
 * Created by lw on 16-12-22.
 */
public class MessageVo {
    private String sender;

    private String receiver;

    private String content;

    private Integer send;

    private Integer chatMode;

    private Integer interestId;

    private String createdAt;

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

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
