package org.zerock.finedu.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.common.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
