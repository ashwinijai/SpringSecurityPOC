package com.example.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg) {
        super(msg);
    }

}
