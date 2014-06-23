package ru.umc806.vmakarenko.exceptions;

/**
 * Created by VMakarenko on 6/22/14.
 */
public class RestException extends Exception{
    public RestException(Exception e){
        super(e);
    }
}
