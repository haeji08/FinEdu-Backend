package org.zerock.finedu.summarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {
        "org.zerock.finedu.summarizer",
        "org.zerock.finedu.common"
})
@EnableJpaRepositories(basePackages = {
        "org.zerock.finedu.common.repository"  // JPA 리포지토리 위치
})
@EntityScan(basePackages = {
        "org.zerock.finedu.common.entity",    // common 모듈 엔티티
})
public class SummarizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SummarizerApplication.class, args);
    }
}
