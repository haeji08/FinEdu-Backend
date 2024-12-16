package org.zerock.finedu.summarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.zerock.finedu.summarizer",    // quiz 모듈
        "org.zerock.finedu.common"  // common 모듈
})
@EnableJpaRepositories(basePackages = {
        "org.zerock.finedu.common.repository"  // JPA 리포지토리 위치
})
@EntityScan(basePackages = {
        "org.zerock.finedu.common.entity"  // 엔티티 스캔 추가
})
public class SummarizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SummarizerApplication.class, args);
    }
}
