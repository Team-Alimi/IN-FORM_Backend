package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.ClubArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClubArticlesRepository extends JpaRepository<ClubArticles, Integer> {
    Page<ClubArticles> findByTitleContaining(String search, Pageable pageable);

    @Query("SELECT a FROM ClubArticles a WHERE a.startDate <= :date2 AND a.dueDate >= :date1")
    List<ClubArticles> findByStartDateLessThanEqualAndDueDateGreaterThanEqual(
            @Param("date2") LocalDate date2, @Param("date1") LocalDate date1
    );

    @Query("SELECT a FROM ClubArticles a WHERE a.startDate <= :date2 AND a.dueDate >= :date1")
    Page<ClubArticles> findMonthlyArticles(
            @Param("date2") LocalDate date2,
            @Param("date1") LocalDate date1,
            Pageable pageable
    );

    Page<ClubArticles> findByDueDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}