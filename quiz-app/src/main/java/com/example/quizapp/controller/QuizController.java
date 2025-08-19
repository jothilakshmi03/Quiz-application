package com.example.quizapp.controller;

import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuizRepository;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*") // allow frontend requests
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Get all quizzes
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Get quiz by id with questions
    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizRepository.findById(id).orElseThrow();
    }

    // Create a quiz
    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // Submit answers (basic check)
    @PostMapping("/{id}/submit")
    public int submitQuiz(@PathVariable Long id, @RequestBody List<String> answers) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectAnswer().equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }
        return score;
    }
}
