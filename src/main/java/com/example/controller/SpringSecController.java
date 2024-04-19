package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecController {

    @GetMapping("/version")
    public String getVersion(Authentication authentication){
        System.out.println("Authentication values - Username = "+authentication.getName());
        return "1.0";
    }
}
