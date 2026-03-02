package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.QuestionDto;
import com.example.OnlineQuiz.Dto.QuizDto;
import com.example.OnlineQuiz.Entity.QuestionEntity;
import com.example.OnlineQuiz.Entity.QuizEntity;
import com.example.OnlineQuiz.Repository.QuizRepository;
import com.example.OnlineQuiz.UpdateDto.QuizUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapper modelMapper;
    public QuizDto add(QuizDto dto) {
        QuizEntity quizEntity=new QuizEntity();
        quizEntity.setDescription(dto.getDescription());
        quizEntity.setName(dto.getName());
        quizEntity.setTotalDuration(dto.getTotalDuration());
        quizEntity.setTotalQuestions(dto.getTotalQuestions());
        QuizEntity saved=quizRepository.save(quizEntity);
        return modelMapper.map(saved, QuizDto.class);
    }

    public List<QuizDto> findAllQuizzes() {
        List<QuizEntity> quizEntity=quizRepository.findAll();
        List<QuizDto> filtered=quizEntity.stream().map(quiz->modelMapper.map(quiz,QuizDto.class)).toList();
        return filtered;
    }

    public QuizDto QuizById(long id) {
        QuizEntity quiz=quizRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Quiz ID"));
        return modelMapper.map(quiz, QuizDto.class);
    }

    public QuizUpdateDto update(long id, QuizUpdateDto dto) {
        QuizEntity quiz=quizRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Quiz ID"));
        quiz.setTotalQuestions(dto.getTotalQuestions());
        quiz.setName(dto.getName());
        quiz.setDescription(dto.getDescription());
        quiz.setTotalDuration(dto.getTotalDuration());
        QuizEntity updated=quizRepository.save(quiz);
        return modelMapper.map(quiz, QuizUpdateDto.class);
    }

    public void delete(long id) {
        quizRepository.deleteById(id);
    }
}
