package com.example.OnlineQuiz.Repository;

import com.example.OnlineQuiz.Entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity,Long> {
}
