package org.zerock.finedu.finedubackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quiz_id;

    @ManyToOne
    @JoinColumn(name = "summary_id")
    private NewsSummaryEntity newsSummary;

    @Column(name = "quiz_title")
    private String quizTitle;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private int answer;

    @Column(name="explanation")
    private String explanation;

    @Column(name = "answer_options", columnDefinition = "json")
    private String options;
}
