package com.example.OnlineQuiz.Controller;

import com.example.OnlineQuiz.Dto.QuizDto;
import com.example.OnlineQuiz.Service.QuizService;
import com.example.OnlineQuiz.UpdateDto.QuizUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<QuizDto> createQuiz(@RequestBody @Valid QuizDto dto)
    {
        QuizDto savedQuiz=quizService.add(dto);
        return new ResponseEntity<>(savedQuiz,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<QuizDto>> findAll()
    {
        return new ResponseEntity<>(quizService.findAllQuizzes(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> findById( @PathVariable  long id)
    {
        QuizDto quiz=quizService.QuizById(id);
        return new ResponseEntity<>(quiz,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuizUpdateDto> updateQuiz(@PathVariable long id, @RequestBody @Valid QuizUpdateDto dto)
    {
       QuizUpdateDto quiz=quizService.update(id,dto);
       return new ResponseEntity<>(quiz,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable long id)
    {
        quizService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
