package org.zerock.finedu.finedubackend.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class QuizAnswerResponse {
    private Long quizId;
    private boolean isCorrect;
    private String explanation;
}
