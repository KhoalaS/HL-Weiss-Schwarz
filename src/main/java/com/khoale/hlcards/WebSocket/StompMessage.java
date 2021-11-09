package com.khoale.hlcards.WebSocket;

public class StompMessage {

    private String name;

    public StompMessage(){}

    public StompMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
