package org.zerock.finedu.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long news_id;

    @Column(name = "news_title")
    private String newsTitle;

    @Column(name = "news_content", columnDefinition = "TEXT")
    private String newsContent;

    @Column(name= "original_url")
    private String originalUrl;

    @Column(name = "crawl_time")
    private LocalDateTime crawlTime;

    @Column(name = "publish_time")
    private LocalDateTime publishTime;

    @OneToOne(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private NewsSummaryEntity newsSummary;

}
