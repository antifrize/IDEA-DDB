package ru.umc806.vmakarenko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.RestException;

/**
 * Created by VMakarenko on 6/22/14.
 */
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler({CannotAddException.class})
    public ModelAndView cannotAddException(Exception e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorText",((CannotAddException)e).getErrorMsg());
        return mav;
    }
    @ExceptionHandler({RestException.class})
    public @ResponseBody boolean restException(Exception e){
        return false;
    }


}
