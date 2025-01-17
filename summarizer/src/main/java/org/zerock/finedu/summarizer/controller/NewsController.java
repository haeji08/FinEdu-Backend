package org.zerock.finedu.summarizer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.finedu.summarizer.dto.response.NewsSummaryResponse;
import org.zerock.finedu.summarizer.service.NewsSummaryService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsSummaryService summaryService;

    @GetMapping("/summary")
    public ResponseEntity<?> getNewsSummary(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 1) Pageable pageable) {

        log.info("해당 키워드의 요약본 요청: {}", keyword);

        try {
            log.info("해당 키워드의 요약본 가져오는 중...: {}", keyword);

            // 에러 처리
            if (keyword == null || keyword.trim().isEmpty()) {
                log.warn("빈 키워드를 받았습니다");
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "status", 400,
                                "message", "키워드가 입력되지 않았습니다."
                        ));
            }

            // 단일 뉴스 요약 반환
            String decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
            log.info("해당 키워드를 디코딩 했습니다: {}", decodedKeyword);

            NewsSummaryResponse summary = summaryService.getRandomSummaryByKeyword(keyword);

            if (summary == null) {
                log.info("해당 키워드에 대한 요약본 없음: {}", keyword);
                return ResponseEntity.status(404)
                        .body(Map.of(
                                "status", 404,
                                "message", "해당 키워드의 뉴스를 찾을 수 없습니다."
                        ));
            }

            log.info("해당 키워드의 요약본 성공적으로 조회!!!: {}", keyword);
            return ResponseEntity.ok(summary);

        } catch (Exception e) {
            log.error("해당 키워드에 대한 요약본 처리 중 오류 : {}", keyword, e);
            return ResponseEntity.status(500)
                    .body(Map.of(
                            "status", 500,
                            "message", "서버 처리 중 오류가 발생했습니다."
                    ));
        }
    }

    @GetMapping("/summary/{summaryId}")
    public ResponseEntity<?> getNewsSummary(@PathVariable Long summaryId) {
        try {
            NewsSummaryResponse summary = summaryService.getSummaryById(summaryId);
            return ResponseEntity.ok(summary);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404)
                    .body(Map.of(
                            "status", 404,
                            "message", "해당 ID의 뉴스를 찾을 수 없습니다."
                    ));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of(
                            "status", 500,
                            "message", "서버 처리 중 오류가 발생했습니다."
                    ));
        }
    }
}