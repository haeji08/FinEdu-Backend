package org.zerock.finedu.finedubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.finedubackend.entity.NewsSummaryEntity;

public interface NewsSummaryRepository extends JpaRepository<NewsSummaryEntity, Long> {
}
