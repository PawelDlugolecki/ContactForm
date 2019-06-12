package com.app.controller;

import com.app.exceptions.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({MyException.class})
    public String myExceptionHandler(MyException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "exceptionPage";
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public String noHandlerFoundException(NoHandlerFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "exceptionPage";
    }

}
