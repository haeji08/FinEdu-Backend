package org.zerock.finedu.finedubackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.finedu.finedubackend.dto.request.OpenAIRequest;
import org.zerock.finedu.finedubackend.dto.response.QuizAnswerResponse;
import org.zerock.finedu.finedubackend.dto.response.QuizResponse;
import org.zerock.finedu.finedubackend.entity.NewsEntity;
import org.zerock.finedu.finedubackend.entity.NewsSummaryEntity;
import org.zerock.finedu.finedubackend.entity.QuizEntity;
import org.zerock.finedu.finedubackend.repository.NewsRepository;
import org.zerock.finedu.finedubackend.repository.NewsSummaryRepository;
import org.zerock.finedu.finedubackend.repository.QuizRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final OpenAIService openAIService;
    private final NewsSummaryRepository newsSummaryRepository;
    private final NewsRepository newsRepository;
    private final QuizRepository quizRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public QuizResponse generateQuiz(Long summaryId) {
        // 1. 뉴스 요약 데이터 조회
        // Optional<NewsSummaryEntity> optionalSummary = newsSummaryRepository.findById(summaryId);
        Optional<NewsEntity> optionalSummary = newsRepository.findById(summaryId);
        if (optionalSummary.isEmpty()) {
            throw new IllegalArgumentException("News Summary not found with ID: " + summaryId);
        }

        //NewsSummaryEntity newsSummary = optionalSummary.get();
        NewsEntity news = optionalSummary.get();

        // 2. OpenAI에 요청할 프롬프트 생성
        String prompt = String.format(
                "다음 요약 내용을 기반으로 반드시 객관식 퀴즈를 생성해줘:\n" +
                        "'%s'.\n\n퀴즈는 다음과 같은 형식으로 작성해줘:\n" +
                        "{\n" +
                        "  \"question\": \"자연스럽고 명확한 문장형 질문 (완전한 문장이어야 하며, 질문에서 물어보는 대상이 명확해야 함)\",\n" +
                        "  \"options\": [\n" +
                        "    \"질문에서 물어보는 대상의 품사와 일치하며 서로 다른 의미를 가진 선택지 1\",\n" +
                        "    \"질문에서 물어보는 대상의 품사와 일치하며 서로 다른 의미를 가진 선택지 2\",\n" +
                        "    \"질문에서 물어보는 대상의 품사와 일치하며 서로 다른 의미를 가진 선택지 3\",\n" +
                        "    \"질문에서 물어보는 대상의 품사와 일치하며 서로 다른 의미를 가진 선택지 4\"\n" +
                        "  ],\n" +
                        "  \"answer\": 1에서 4 사이의 숫자 중 랜덤하게 선택된 정답 번호,\n" +
                        "  \"explanation\": \"정답은 ~번입니다. 이유: 해당 선택지가 정답인 이유를 명확하고 자연스럽게 설명. 문장은 항상 '입니다'로 끝내세요.\"\n" +
                        "}\n\n" +
                        "반드시 지켜야 할 사항:\n" +
                        "1. 질문(question)은 완전한 문장형이어야 하며, 모호하지 않고 명확하게 작성해야 하고 요약 내용과 직접적으로 관련된 질문을 만들어야 합니다. 문법적으로 올바르고 자연스럽게 작성해야 합니다.\n" +
                        "2. 선택지는 질문에서 물어보는 대상의 품사와 일치해야 하며, 서로 다른 의미를 가지도록 작성해야 합니다. 그리고 자연스럽고 문법적으로 올바르게 작성되어야 합니다.\n" +
                        "3. 정답(answer)은 1부터 4 사이의 랜덤한 숫자여야 합니다.\n" +
                        "4. 해설(explanation)은 반드시 '정답은 ~번입니다.'로 시작한 후, 그 이유를 명확하고 구체적으로 설명해야 합니다. 문장은 항상 '입니다'로 끝나야 합니다.\n" +
                        "5. 결과는 JSON 형식 그대로 반환해야 합니다.",
                news.getNewsContent()
        );

        // 3. OpenAI API 호출
        OpenAIRequest openAIRequest = OpenAIRequest.builder()
                .prompt(prompt)
                .build();

        String openAIResponse = openAIService.getResponseFromOpenAI(openAIRequest);

        try {
            // 4. 응답 파싱
            JsonNode responseJson = objectMapper.readTree(openAIResponse);

            // 필드 추출
            String question = responseJson.path("question").asText();
            JsonNode optionsNode = responseJson.path("options"); // 선택지
            int answer = responseJson.path("answer").asInt(); // 정답 인덱스
            String explanation = responseJson.path("explanation").asText(); // 해설

            // optionsNode를 List<String>으로 변환
            List<String> options = new ArrayList<>();
            if (optionsNode.isArray()) {
                for (JsonNode option : optionsNode) {
                    options.add(option.asText());
                }
            }

            // options를 JSON String으로 변환하여 QuizEntity에 저장
            String optionsJson = objectMapper.writeValueAsString(options);

            // 5. QuizEntity 저장
            QuizEntity quiz = new QuizEntity();
//            quiz.setNewsSummary(newsSummary);
            quiz.setQuizTitle(news.getNewsTitle());
            quiz.setQuestion(question);
            quiz.setOptions(optionsJson);
            quiz.setAnswer(answer);
            quiz.setExplanation(explanation);
            QuizEntity savedQuiz = quizRepository.save(quiz);

            // 6. QuizResponse DTO로 변환
            QuizResponse quizResponse = QuizResponse.builder()
                    .quiz_id(savedQuiz.getQuiz_id())
                    .news_id(news.getNews_id()) // 뉴스 ID
//                    .summary_id(newsSummary.getSummary_id())    // 요약 ID
                    .quiz_title(savedQuiz.getQuizTitle())
                    .question(savedQuiz.getQuestion())
                    .options(options)
                    .build();

            return quizResponse;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response", e);
        }
    }

    public QuizAnswerResponse checkAnswer(Long quizId, String userAnswer) {
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        boolean isCorrect = Integer.parseInt(userAnswer) == quiz.getAnswer();
        String explanation = quiz.getExplanation();

        return QuizAnswerResponse.builder()
                .quizId(quizId)
                .isCorrect(isCorrect)
                .explanation(explanation)
                .build();
    }
}
