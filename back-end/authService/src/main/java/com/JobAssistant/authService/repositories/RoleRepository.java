package com.JobAssistant.authService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.JobAssistant.authService.models.ERole;
import com.JobAssistant.authService.models.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
  }