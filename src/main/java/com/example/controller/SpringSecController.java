package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecController {

    @GetMapping("/version")
    public String getVersion(){
        return "1.0";
    }
}
