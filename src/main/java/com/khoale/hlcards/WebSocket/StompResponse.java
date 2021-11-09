package com.khoale.hlcards.WebSocket;

public class StompResponse {

    private String content;

    public StompResponse(){}

    public StompResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
