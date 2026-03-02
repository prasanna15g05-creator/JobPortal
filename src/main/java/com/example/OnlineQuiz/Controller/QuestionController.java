package com.example.OnlineQuiz.Controller;

import com.example.OnlineQuiz.Dto.QuestionResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;


import com.example.OnlineQuiz.Dto.QuestionDto;
import com.example.OnlineQuiz.Dto.QuizDto;
import com.example.OnlineQuiz.Service.QuestionService;
import com.example.OnlineQuiz.Service.QuizService;
import com.example.OnlineQuiz.UpdateDto.QuestionUpdateDto;
import com.example.OnlineQuiz.UpdateDto.QuizUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody @Valid QuestionDto dto)
    {
        QuestionDto savedQuestion=questionService.add(dto);
        return new ResponseEntity<>(savedQuestion,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<QuestionDto>> findAll()
    {
        return new ResponseEntity<>(questionService.findAllQuestions(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findById( @PathVariable  long id)
    {
        QuestionDto question=questionService.QuestionById(id);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }

    @GetMapping("/quizId/{id}")
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestionsByQuiz(@PathVariable long id)
    {
        List<QuestionResponseDto> questions=questionService.findByQuizId(id);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionUpdateDto> updateQuestion(@PathVariable long id, @RequestBody @Valid QuestionUpdateDto dto)
    {
        QuestionUpdateDto question=questionService.update(id,dto);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable long id)
    {
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
