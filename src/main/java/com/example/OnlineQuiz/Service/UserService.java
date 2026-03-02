package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.AuthDto;
import com.example.OnlineQuiz.Dto.AuthResponseDto;
import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Entity.UserEntity;
import com.example.OnlineQuiz.Repository.UserRepository;
import com.example.OnlineQuiz.UpdateDto.UserUpdateDto;
import com.example.OnlineQuiz.Utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    private ModelMapper modelMapper;


    private final PasswordEncoder passwordEncoder;

    private final  AuthenticationManager authenticationManager;


    private final JwtUtil jwtUtil;




    public UserUpdateDto update(long id, UserUpdateDto dto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));

  if(dto.getEmail()!=null && !dto.getEmail().isEmpty()) {
      userEntity.setEmail(dto.getEmail());
  }
  if(dto.getName()!=null && !dto.getName().isEmpty()) {
      userEntity.setName(dto.getName());
  }
        if(dto.getPassword()!=null && !dto.getPassword().isEmpty()) {
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        UserEntity saved=userRepository.save(userEntity);
        return modelMapper.map(saved,UserUpdateDto.class);
    }

    public UserDto createUser(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setRole(dto.getRole());
        System.out.print(userEntity.getEmail()+userEntity.getPassword()+userEntity.getName()+userEntity.getRole());
        UserEntity saved=userRepository.save(userEntity);
        return modelMapper.map(saved,UserDto.class);
    }

    public List<UserDto> findAll() {
        List<UserEntity> users=userRepository.findAll();
        List<UserDto> filtered=users.stream().map((user)->modelMapper.map(user,UserDto.class)).toList();
        return filtered;
    }

    public UserDto UserById(long id) {
        UserEntity user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return modelMapper.map(user,UserDto.class);
    }

    public void  deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public AuthResponseDto checkUser(AuthDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );


        UserEntity user = userRepository.findByEmail(
                dto.getEmail()
        ).orElseThrow(() -> new RuntimeException("User not found"));


        String token = jwtUtil.generateToken(user.getEmail());


        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setId(user.getId());
        response.setRole(user.getRole().toString());
        response.setEmail(user.getEmail());

        return response;
    }
}
