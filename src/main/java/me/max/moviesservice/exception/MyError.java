package me.max.moviesservice.exception;

/**
 * Created by amxik on 27.04.2019.
 */
public class MyError {
    private int responseCode;
    private String message;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

