package com.JobAssistant.authService.controllers;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobAssistant.authService.models.AuthenticationRequest;
import com.JobAssistant.authService.models.AuthenticationResponse;
import com.JobAssistant.authService.models.RegisterRequest;
import com.JobAssistant.authService.services.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    @Autowired
    private AuthenticationService service;

   @PostMapping("/register")
   public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
     return ResponseEntity.ok(service.register(request));
   }
   
   @PostMapping("/authenticate")
   public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
   }


   
}
