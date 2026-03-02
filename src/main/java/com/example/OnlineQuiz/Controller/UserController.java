package com.example.OnlineQuiz.Controller;

import com.example.OnlineQuiz.Dto.AuthDto;
import com.example.OnlineQuiz.Dto.AuthResponseDto;
import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Service.UserService;
import com.example.OnlineQuiz.UpdateDto.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto)
    {
        UserDto savedUser=userService.createUser(dto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable  long id,@RequestBody @Valid UserUpdateDto dto){
     UserUpdateDto updated=userService.update(id,dto);
     return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id)
    {
        UserDto userDet=userService.UserById(id);
        return new ResponseEntity<>(userDet,HttpStatus.OK);
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> authUser(@RequestBody AuthDto dto)
    {
        AuthResponseDto verified=  userService.checkUser(dto);
        return new ResponseEntity<>(verified,HttpStatus.OK);
    }

}
