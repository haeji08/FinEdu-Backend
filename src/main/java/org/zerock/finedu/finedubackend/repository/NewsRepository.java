package org.zerock.finedu.finedubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.finedu.finedubackend.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
