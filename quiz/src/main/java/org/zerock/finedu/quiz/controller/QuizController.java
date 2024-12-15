package org.zerock.finedu.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.finedu.quiz.dto.request.QuizAnswerRequest;
import org.zerock.finedu.quiz.dto.response.QuizAnswerResponse;
import org.zerock.finedu.quiz.dto.response.QuizResponse;
import org.zerock.finedu.quiz.service.QuizService;

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

    @PostMapping("/{quizId}/answer")
    public ResponseEntity<QuizAnswerResponse> submitAnswer(@PathVariable Long quizId, @RequestBody QuizAnswerRequest quizAnswerRequest) {
        QuizAnswerResponse response = quizService.checkAnswer(quizId, quizAnswerRequest.getUserAnswer());
        return ResponseEntity.ok(response);
    }
}
