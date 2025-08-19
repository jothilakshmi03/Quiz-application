package com.example.quizapp.controller;


import com.example.quizapp.dto.QuestionDto;
import com.example.quizapp.dto.SubmitRequest;
import com.example.quizapp.dto.SubmitResponse;
import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionRepository questionRepository;
    public QuestionController(QuestionRepository questionRepository) { this.questionRepository = questionRepository; }

    @GetMapping
    public List<QuestionDto> getRandom(@RequestParam(defaultValue = "5") int limit) {
        List<Question> all = questionRepository.findAll();
        Collections.shuffle(all);
        return all.stream().limit(Math.min(limit, all.size())).map(q -> {
            QuestionDto dto = new QuestionDto();
            dto.setId(q.getId());
            dto.setQuestionText(q.getQuestion());
            dto.setOptionA(q.getOptionA());
            dto.setOptionB(q.getOptionB());
            dto.setOptionC(q.getOptionC());
            dto.setOptionD(q.getOptionD());
            return dto; // do NOT expose correctOption
        }).collect(Collectors.toList());
    }
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }
    @DeleteMapping
    public String deleteAllQuestions() {
        questionRepository.deleteAll();
        return "All questions deleted successfully!";
    }

    // ✅ NEW: Get single question (with correct answer)
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        return questionRepository.findById(id)
                .map(ResponseEntity::ok) // return 200 + JSON if found
                .orElse(ResponseEntity.notFound().build()); // return proper 404
    }

    @PostMapping("/submit")
    public SubmitResponse submitQuiz(@RequestBody SubmitRequest submitRequest) {
        int score = 0;
        int total = submitRequest.getAnswers().size();

        for (Map.Entry<Long, String> entry : submitRequest.getAnswers().entrySet()) {
            Long questionId = entry.getKey();
            String selectedOption = entry.getValue();

            Question q = questionRepository.findById(questionId).orElse(null);
            if (q != null && q.getCorrectAnswer().equalsIgnoreCase(selectedOption)) {
                score++;
            }
        }

        return new SubmitResponse(score, total);
    }


}
