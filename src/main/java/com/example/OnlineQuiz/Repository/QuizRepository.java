package com.example.OnlineQuiz.Repository;

import com.example.OnlineQuiz.Entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Long> {
}
