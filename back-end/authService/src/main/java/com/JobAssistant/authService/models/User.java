package com.JobAssistant.authService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.lang.NonNull;

import java.util.Collection;
import java.util.HashSet;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Users")
public class User implements UserDetails {
    @Id
    private String id;

    @NonNull
    private String email;

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;

    @NonNull
    private String password; 

    @DBRef
    private Collection<? extends GrantedAuthority> role = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getUsername() {
        return email;
    }


    
}
