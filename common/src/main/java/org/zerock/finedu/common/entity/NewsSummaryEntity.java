package org.zerock.finedu.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="news_summary")
public class NewsSummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long summary_id;

    @OneToOne
    @JoinColumn(name = "news_id", referencedColumnName = "news_id")
    private NewsEntity news;

    @Column(name = "summary_title")
    private String summaryTitle;

    @Column(name = "summary_content")
    private String summaryContent;

    @Column(name = "keyword")
    private String keyword;

    @OneToMany(mappedBy = "newsSummary",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<QuizEntity> quizList;

}
