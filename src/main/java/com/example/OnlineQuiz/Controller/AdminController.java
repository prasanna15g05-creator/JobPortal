package com.example.OnlineQuiz.Controller;

import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Service.UserService;
import com.example.OnlineQuiz.UpdateDto.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto)
    {
        UserDto savedUser=userService.createUser(dto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable  long id, @RequestBody @Valid UserUpdateDto dto){
        UserUpdateDto updated=userService.update(id,dto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
