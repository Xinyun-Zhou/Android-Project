package com.example.mymarketplaceapp.models;

/**
 * Chat Class
 * Load and store the message between sender and receiver
 * @author u7326123 Rita Zhou
 */
public class Chat {
    private String sender;
    private String receiver;
    private String message;

    public Chat(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Chat(){
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
