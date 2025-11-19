package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.SchoolArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolArticlesRepository extends JpaRepository<SchoolArticles, Integer> {
    Page<SchoolArticles> findByTitleContaining(String search, Pageable pageable);
    Page<SchoolArticles> findByCategories_CategoryName(String categoryName, Pageable pageable);
    Page<SchoolArticles> findByTitleContainingAndCategories_CategoryName(String search, String categoryName, Pageable pageable);
}