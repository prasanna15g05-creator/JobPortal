package com.example.OnlineQuiz.Repository;

import com.example.OnlineQuiz.Entity.UserEntity;
import com.example.OnlineQuiz.Utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private UserEntity user;

    @BeforeEach
    public void setup(){
        user=new UserEntity();
        user.setName("doe");
        user.setRole(AppUtils.Roles.ADMIN);
        user.setPassword("123Doe@!");
        user.setEmail("doe@gmail.com");
    }

    @Test
    public void givenUserObject_whenSaved_thenReturnSavedUser(){
        UserEntity saved=userRepository.save(user);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void givenUserEmailAndPassword_whenChecked_thenReturnUserDetails(){
        userRepository.save(user);
        UserEntity userDetails=userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
        assertThat(userDetails).isNotNull();

    }
}
