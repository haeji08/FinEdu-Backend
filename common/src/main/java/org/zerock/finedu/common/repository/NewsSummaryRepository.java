package org.zerock.finedu.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.common.entity.NewsSummaryEntity;

import java.util.List;

public interface NewsSummaryRepository extends JpaRepository<NewsSummaryEntity, Long> {
    Page<NewsSummaryEntity> findByKeywordContainingOrderByNews_PublishTimeDesc(String keyword, Pageable pageable);
    List<NewsSummaryEntity> findByKeywordContaining(String keyword);

}