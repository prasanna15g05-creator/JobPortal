package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.QuizDto;
import com.example.OnlineQuiz.Dto.ResultDto;
import com.example.OnlineQuiz.Dto.ResultResponseDto;
import com.example.OnlineQuiz.Entity.QuestionEntity;
import com.example.OnlineQuiz.Entity.ResultEntity;
import com.example.OnlineQuiz.Entity.UserEntity;
import com.example.OnlineQuiz.Repository.QuestionRepository;
import com.example.OnlineQuiz.Repository.ResultRepository;
import com.example.OnlineQuiz.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    public ResultResponseDto add(ResultDto dto) {
        ResultEntity resultEntity=new ResultEntity();
        resultEntity.setQuizId(dto.getQuizId());
        resultEntity.setUserId(dto.getUserId());
        List<QuestionEntity> questions=questionRepository.findByQuizId(dto.getQuizId());

        Map<Long,QuestionEntity> question=questions.stream().collect(Collectors.toMap(QuestionEntity::getId,q->q));

        long totalCorrect = dto.getTotalAnswers().stream().filter(answer -> {
            QuestionEntity quest = question.get(answer.getQuestionId());
            if (quest == null) return false;


            String submitted = answer.getOption().trim();
            String correct = quest.getCorrectOption().trim();

            return submitted.equalsIgnoreCase(correct);
        }).count();
        double finalScore=((double)totalCorrect/ dto.getTotalAnswers().size())*100;
        ResultResponseDto response=new ResultResponseDto();
        response.setFinalScore(finalScore);
        resultEntity.setFinalScore(finalScore);
        UserEntity user=userRepository.findById(dto.getUserId()).orElseThrow(()->new RuntimeException("User Not Found"));
         emailService.sendResultEmail(user.getEmail(),finalScore);
        ResultEntity saved=resultRepository.save(resultEntity);
        return response;
    }

    public List<ResultDto> findAllResults() {
        List<ResultEntity> results=resultRepository.findAll();
        List<ResultDto> filtered=results.stream().map(result->modelMapper.map(result, ResultDto.class)).toList();
        return filtered;
    }

    public ResultDto ResultById(long id) {
        ResultEntity result=resultRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Result ID"));
        return modelMapper.map(result, ResultDto.class);
    }
}
