package org.zerock.finedu.summarizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zerock.finedu.common.dto.request.OpenAIRequest;
import org.zerock.finedu.common.entity.NewsEntity;
import org.zerock.finedu.common.repository.NewsRepository;
import org.zerock.finedu.common.service.OpenAIService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsSummarizeService {

    private final NewsRepository newsRepository;
    private final OpenAIService openAIService;
    private final SummarizeTransactionalService transactionalService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 0 * * * *")
    public void summarizeUnprocessedNews() {
        int batchSize = 100;
        PageRequest pageRequest = PageRequest.of(0, batchSize);
        List<NewsEntity> unprocessedNews = newsRepository.findByNewsSummaryIsNullOrderByCrawlTimeDesc(pageRequest);

        for (NewsEntity news : unprocessedNews) {
            try {
                // OpenAI API 요청을 위한 프롬프트 생성
                String prompt = String.format(
                        "다음 금융 뉴스 내용을 200자 이내로 요약하고, 관련된 주요 금융 키워드를 3개 추출해주세요. 다음과 같은 JSON 형식으로 응답해주세요:\n" +
                                "{\n" +
                                "  \"summary\": \"요약된 내용 (금융 관련 중요 정보 위주로)\",\n" +
                                "  \"keywords\": \"금융키워드1,금융키워드2,금융키워드3\"\n" +
                                "}\n\n" +
                                "뉴스 내용:\n%s",
                        news.getNewsContent()
                );

                OpenAIRequest openAIRequest = OpenAIRequest.builder()
                        .prompt(prompt)
                        .build();

                // OpenAI API 호출 및 응답 파싱
                String response = openAIService.getResponseFromOpenAI(openAIRequest);
                JsonNode responseJson = objectMapper.readTree(response);

                // 요약 엔티티 생성 및 저장
                transactionalService.saveSummary(news, responseJson);

            } catch (Exception e) {
                // 개별 뉴스 처리 실패 시 로그 기록 후 다음 뉴스 처리 진행
                System.err.println("Failed to summarize news ID: " + news.getNews_id() + ". Error: " + e.getMessage());
            }
        }
    }
}