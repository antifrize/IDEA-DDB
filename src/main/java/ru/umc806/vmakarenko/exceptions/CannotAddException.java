package ru.umc806.vmakarenko.exceptions;

/**
 * Created by VMakarenko on 6/22/14.
 */
public class CannotAddException extends Exception {
    private String errorMsg;
    public CannotAddException(String errorMsg){
        this.errorMsg = errorMsg;
    }
    public String getErrorMsg(){
        return errorMsg;
    }
}
