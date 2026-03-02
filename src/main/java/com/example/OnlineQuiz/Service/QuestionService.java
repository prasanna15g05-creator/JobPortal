package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.QuestionDto;
import com.example.OnlineQuiz.Dto.QuestionResponseDto;
import com.example.OnlineQuiz.Entity.QuestionEntity;
import com.example.OnlineQuiz.Entity.QuizEntity;
import com.example.OnlineQuiz.Repository.QuestionRepository;
import com.example.OnlineQuiz.Repository.QuizRepository;
import com.example.OnlineQuiz.UpdateDto.QuestionUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapper modelMapper;

    public QuestionDto add(QuestionDto dto) {
        QuestionEntity questionEntity=new QuestionEntity();
        questionEntity.setQuestion(dto.getQuestion());
        questionEntity.setOption1(dto.getOption1());
        questionEntity.setOption2(dto.getOption2());
        questionEntity.setOption3(dto.getOption3());
        questionEntity.setOption4(dto.getOption4());
        questionEntity.setCorrectOption(dto.getCorrectOption());

        QuizEntity quiz=quizRepository.findById(dto.getQuizId()).orElseThrow(()->new RuntimeException("Quiz Not Found"));
        questionEntity.setQuiz(quiz);
        QuestionEntity saved=questionRepository.save(questionEntity);
        return modelMapper.map(saved, QuestionDto.class);
    }

    public List<QuestionDto> findAllQuestions() {
        List<QuestionEntity> questions=questionRepository.findAll();
        List<QuestionDto> filtered=questions.stream().map(question->modelMapper.map(question, QuestionDto.class)).toList();
        return filtered;
    }

    public QuestionDto QuestionById(long id) {
        QuestionEntity question=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found"));
        return modelMapper.map(question, QuestionDto.class);
    }

    public QuestionUpdateDto update(long id, QuestionUpdateDto dto) {
        QuestionEntity questionEntity=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found"));
        if(dto.getQuestion()!=null && !dto.getQuestion().isEmpty()) {
            questionEntity.setQuestion(dto.getQuestion());
        }
        if(dto.getOption1()!=null && !dto.getOption1().isEmpty()) {
            questionEntity.setOption1(dto.getOption1());
        }
        if(dto.getOption2()!=null && !dto.getOption2().isEmpty()) {
            questionEntity.setOption2(dto.getOption2());
        }
        if(dto.getOption3()!=null && !dto.getOption3().isEmpty()) {
            questionEntity.setOption3(dto.getOption3());
        }
        if(dto.getOption4()!=null && !dto.getOption4().isEmpty()) {
            questionEntity.setOption4(dto.getOption4());
        }
        if(dto.getCorrectOption()!=null && !dto.getCorrectOption().isEmpty()) {
            questionEntity.setCorrectOption(dto.getCorrectOption());
        }
        QuizEntity quiz=quizRepository.findById(dto.getQuizId()).orElseThrow(()->new RuntimeException("Quiz Not Found"));
        questionEntity.setQuiz(quiz);
        QuestionEntity updated=questionRepository.save(questionEntity);
        return modelMapper.map(updated, QuestionUpdateDto.class);
    }

    public void delete(long id) {
        QuestionEntity question=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found "));
        questionRepository.deleteById(id);
    }

    public List<QuestionResponseDto> findByQuizId(long id) {

        List<QuestionEntity> questions = questionRepository.findByQuizId(id);
        List<QuestionResponseDto> filtered=questions.stream().map(question->modelMapper.map(question, QuestionResponseDto.class)).toList();
        return filtered;
    }
}