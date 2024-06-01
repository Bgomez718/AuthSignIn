package com.JobAssistant.authService.repositories;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.JobAssistant.authService.models.User;
public interface UserRepository extends MongoRepository<User , String>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
