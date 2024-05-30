package com.JobAssistant.authService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    @GetMapping("/login")
    public String getMethodName() {
        return "login";
    }
    
}
