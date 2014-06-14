package ru.umc806.vmakarenko.util;

/**
 * Created by VMakarenko on 5/27/14.
 */
public class ScheduleException extends Exception {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public ScheduleException(String code, String message){
        super(message);
        this.code = code;
    }
}
