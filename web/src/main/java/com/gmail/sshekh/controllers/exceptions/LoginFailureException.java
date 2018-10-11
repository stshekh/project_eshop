package com.gmail.sshekh.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such permission")
public class LoginFailureException extends RuntimeException {
    public LoginFailureException() {
        super();
    }
    public LoginFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    public LoginFailureException(String message) {
        super(message);
    }
    public LoginFailureException(Throwable cause) {
        super(cause);
    }
}
