package org.zerock.finedu.finedubackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.finedubackend.entity.NewsSummaryEntity;

import java.util.List;

public interface NewsSummaryRepository extends JpaRepository<NewsSummaryEntity, Long> {
    Page<NewsSummaryEntity> findByKeywordContainingOrderByNews_PublishTimeDesc(String keyword, Pageable pageable);
    List<NewsSummaryEntity> findByKeywordContaining(String keyword);

}