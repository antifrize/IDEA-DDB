package ru.umc806.vmakarenko.util.rest;

/**
 * Created by VMakarenko on 6/12/14.
 */
public class Result {
    private boolean success;
    private String result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
