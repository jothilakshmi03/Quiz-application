package com.example.quizapp.repository;


import com.example.quizapp.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ResultRepository extends JpaRepository<Result, Long> {
}
