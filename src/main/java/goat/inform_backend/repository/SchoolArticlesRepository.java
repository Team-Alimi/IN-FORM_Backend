package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.SchoolArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchoolArticlesRepository extends JpaRepository<SchoolArticles, Integer> {
    Page<SchoolArticles> findByTitleContaining(String search, Pageable pageable);
    Page<SchoolArticles> findByCategories_CategoryName(String categoryName, Pageable pageable);
    Page<SchoolArticles> findByTitleContainingAndCategories_CategoryName(String search, String categoryName, Pageable pageable);

    @Query("SELECT a FROM SchoolArticles a WHERE a.startDate <= :date2 AND a.dueDate >= :date1")
    List<SchoolArticles> findByStartDateLessThanEqualAndDueDateGreaterThanEqual(
            @Param("date2") LocalDate date2, @Param("date1") LocalDate date1);

    @Query("SELECT a FROM SchoolArticles a WHERE a.startDate <= :date2 AND a.dueDate >= :date1")
    Page<SchoolArticles> findMonthlyArticles(
            @Param("date2") LocalDate date2,
            @Param("date1") LocalDate date1,
            Pageable pageable
    );
}