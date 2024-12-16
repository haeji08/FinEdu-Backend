package org.zerock.finedu.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.zerock.finedu.quiz",
        "org.zerock.finedu.common"
})
@EnableJpaRepositories(basePackages = {
        "org.zerock.finedu.common.repository"
})
@EntityScan(basePackages = {
        "org.zerock.finedu.common.entity"
})
public class QuizApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }
}