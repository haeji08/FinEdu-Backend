package org.zerock.finedu.summarizer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.finedu.common.entity.NewsSummaryEntity;
import org.zerock.finedu.common.repository.NewsSummaryRepository;
import org.zerock.finedu.summarizer.dto.response.NewsSummaryResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsSummaryService {

    private final NewsSummaryRepository summaryRepository;

    @Transactional(readOnly = true)
    public List<NewsSummaryResponse> getSummariesByKeyword(String keyword, Pageable pageable) {
        Page<NewsSummaryEntity> summaries = summaryRepository.findByKeywordContainingOrderByNews_PublishTimeDesc(
                keyword, pageable);

        return summaries.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NewsSummaryResponse getSummaryById(Long summaryId) {
        NewsSummaryEntity summary = summaryRepository.findById(summaryId)
                .orElseThrow(() -> new IllegalArgumentException("Summary not found with id: " + summaryId));

        return convertToResponse(summary);
    }

    private NewsSummaryResponse convertToResponse(NewsSummaryEntity entity) {
        return NewsSummaryResponse.builder()
                .summaryId(entity.getSummary_id())
                .newsId(entity.getNews().getNews_id())
                .summaryTitle(entity.getSummaryTitle())
                .summaryContent(entity.getSummaryContent())
                .keywords(entity.getKeyword())
                .originalUrl(entity.getNews().getOriginalUrl())
                .publishTime(entity.getNews().getPublishTime())
                .build();
    }

    @Transactional(readOnly = true)
    public NewsSummaryResponse getRandomSummaryByKeyword(String keyword) {
        List<NewsSummaryEntity> summaries = summaryRepository
                .findByKeywordContaining(keyword);

        if (summaries.isEmpty()) {
            return null;
        }

        // 랜덤으로 1개 선택
        int randomIndex = (int) (Math.random() * summaries.size());
        NewsSummaryEntity randomSummary = summaries.get(randomIndex);

        return convertToResponse(randomSummary);
    }
}