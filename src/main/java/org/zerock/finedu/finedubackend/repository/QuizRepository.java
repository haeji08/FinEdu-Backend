package org.zerock.finedu.finedubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.finedubackend.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
