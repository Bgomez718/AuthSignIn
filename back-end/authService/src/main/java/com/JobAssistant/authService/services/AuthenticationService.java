package com.JobAssistant.authService.services;


import javax.management.RuntimeErrorException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobAssistant.authService.config.JwtService;
import com.JobAssistant.authService.models.AuthenticationRequest;
import com.JobAssistant.authService.models.AuthenticationResponse;
import com.JobAssistant.authService.models.RegisterRequest;
import com.JobAssistant.authService.models.User;
import com.JobAssistant.authService.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        if(userExist(request.getEmail())){
          throw new RuntimeErrorException(null, "User already exist with email " + request.getEmail());
        }

        var user = User.builder()
        .firstname(request.getFirstName())
        .lastname(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword())) 
        .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        .token(jwtToken)
        
        .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        .token(jwtToken)
        
        .build();


    }

    public boolean userExist(String email){
        if(repository.findByEmail(email) != null){
            return true;
        } else {
            return false;
        }
    }
}
