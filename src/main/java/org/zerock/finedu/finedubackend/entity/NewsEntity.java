package org.zerock.finedu.finedubackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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

    @Column(name = "news_content")
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
