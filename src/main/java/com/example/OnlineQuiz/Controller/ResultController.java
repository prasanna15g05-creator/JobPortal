package com.example.OnlineQuiz.Controller;

import com.example.OnlineQuiz.Dto.QuizDto;
import com.example.OnlineQuiz.Dto.ResultDto;
import com.example.OnlineQuiz.Dto.ResultResponseDto;
import com.example.OnlineQuiz.Service.QuizService;
import com.example.OnlineQuiz.Service.ResultService;
import com.example.OnlineQuiz.UpdateDto.QuizUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
        @RequestMapping("/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/add")
    public ResponseEntity<ResultResponseDto> createQuiz(@RequestBody @Valid ResultDto dto)
    {
        ResultResponseDto savedResult=resultService.add(dto);
        return new ResponseEntity<>(savedResult, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<ResultDto>> findAll()
    {
        return new ResponseEntity<>(resultService.findAllResults(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> findById( @PathVariable long id)
    {
        ResultDto quiz=resultService.ResultById(id);
        return new ResponseEntity<>(quiz,HttpStatus.OK);
    }

}
