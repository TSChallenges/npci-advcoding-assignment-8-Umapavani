package com.mystore.app.entity;

public class CustomException{
    private String status;
    private String message;

    public CustomException() {
    }

    public CustomException(String status,String message) {
        this.message = message;
        this.status= status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
