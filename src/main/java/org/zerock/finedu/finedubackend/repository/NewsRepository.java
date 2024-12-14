package org.zerock.finedu.finedubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.finedu.finedubackend.entity.NewsEntity;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query(value = "SELECT n FROM NewsEntity n WHERE n.newsSummary IS NULL ORDER BY n.crawlTime DESC")
    List<NewsEntity> findByNewsSummaryIsNullOrderByCrawlTimeDesc(Pageable pageable);
}
