package com.marvel.api.repository;

import com.marvel.api.model.entity.QueryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryLogRepository extends JpaRepository<QueryLog, Long> {
}
