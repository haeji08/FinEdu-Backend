package org.zerock.finedu.summarizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.finedu.common.entity.NewsEntity;
import org.zerock.finedu.common.entity.NewsSummaryEntity;
import org.zerock.finedu.common.repository.NewsSummaryRepository;

@Service
@RequiredArgsConstructor
public class SummarizeTransactionalService {

    private final NewsSummaryRepository summaryRepository;

    @Transactional
    public void saveSummary(NewsEntity news, JsonNode responseJson) {
        NewsSummaryEntity summary = new NewsSummaryEntity();
        summary.setNews(news);
        summary.setSummaryTitle(generateSummaryTitle(news.getNewsTitle()));
        summary.setSummaryContent(responseJson.get("summary").asText());
        summary.setKeyword(responseJson.get("keywords").asText());
        summaryRepository.save(summary);
    }

    private String generateSummaryTitle(String newsTitle) {
        // 뉴스 제목에서 [] 등의 불필요한 태그 제거
        return newsTitle.replaceAll("\\[.*?\\]", "").trim();
    }
}