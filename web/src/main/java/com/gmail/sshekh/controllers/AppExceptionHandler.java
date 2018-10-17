package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.exceptions.LoginFailureException;
import com.gmail.sshekh.controllers.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(LoginFailureException.class)
    public String loginErrorHandler(HttpServletRequest req, Exception e) {
        req.setAttribute("exception", e);
        req.setAttribute("url", req.getRequestURL());
        return "login";
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e) {
        req.setAttribute("exception", e);
        req.setAttribute("url", req.getRequestURL());
        return "errors";
    }
}
