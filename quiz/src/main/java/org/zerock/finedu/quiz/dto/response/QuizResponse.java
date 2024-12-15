package org.zerock.finedu.quiz.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuizResponse {
    private Long quiz_id;        // 퀴즈 ID
    private Long news_id;        // 뉴스 ID
    private Long summary_id;     // 요약 ID
    private String quiz_title;   // 퀴즈 제목
    private String question;     // 퀴즈 질문
    private List<String> options; // 선택지
}
