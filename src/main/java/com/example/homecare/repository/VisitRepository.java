package com.example.homecare.repository;

import com.example.homecare.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, String> {
    @Query("SELECT COUNT(*) FROM Visit v WHERE v.month = :month AND v.year = :year")
    Long countVisit(@Param("month") String month, @Param("year") String year);
    @Query("SELECT CAST(v.month AS int), COUNT(v) FROM Visit v WHERE v.year = :year GROUP BY v.month ORDER BY v.month")
    List<Object[]> countVisitByYear(@Param("year") String year);
}
