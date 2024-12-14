package org.zerock.finedu.finedubackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.finedu.finedubackend.dto.response.QuizResponse;
import org.zerock.finedu.finedubackend.service.QuizService;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/generate")
    public ResponseEntity<QuizResponse> generateQuiz(@RequestParam Long summaryId) {
        QuizResponse quiz = quizService.generateQuiz(summaryId);
        return ResponseEntity.ok(quiz);
    }


}
