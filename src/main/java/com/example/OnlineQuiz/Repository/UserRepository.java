package com.example.OnlineQuiz.Repository;

import com.example.OnlineQuiz.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String email);
}
