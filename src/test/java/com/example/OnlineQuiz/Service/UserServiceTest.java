package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Entity.UserEntity;
import com.example.OnlineQuiz.Repository.UserRepository;
import com.example.OnlineQuiz.Utils.AppUtils;
import com.example.OnlineQuiz.Utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;
    private UserEntity userEntity;
    @BeforeEach
    public void setUp()
    {
        userEntity=new UserEntity();
        userEntity.setEmail("john@gmail.com");
        userEntity.setRole(AppUtils.Roles.ADMIN);
        userEntity.setName("john");
        userEntity.setPassword("john123@");
    }

    @Test
    public void givenUserDto_whenSaved_thenReturnSavedDto() {

        UserDto userDto = new UserDto();
        userDto.setEmail("john@gmail.com");
        userDto.setName("john");
        userDto.setPassword("john123@");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("john@gmail.com");
        userEntity.setName("john");
        userEntity.setPassword("encodedPassword");

        given(passwordEncoder.encode(any()))
                .willReturn("encodedPassword");
        given(userRepository.save(any(UserEntity.class)))
                .willReturn(userEntity);


        given(modelMapper.map(any(UserEntity.class), any(Class.class)))
                .willReturn(userDto);


        UserDto savedDto = userService.createUser(userDto);


        assertThat(savedDto).isNotNull();
        assertThat(savedDto.getEmail()).isEqualTo("john@gmail.com");
    }
}
