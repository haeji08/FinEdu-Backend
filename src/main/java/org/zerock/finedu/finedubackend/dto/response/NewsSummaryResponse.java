package org.zerock.finedu.finedubackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsSummaryResponse {
    private Long summaryId;
    private Long newsId;
    private String summaryTitle;
    private String summaryContent;
    private String keywords;
    private String originalUrl;
    private LocalDateTime publishTime;
}