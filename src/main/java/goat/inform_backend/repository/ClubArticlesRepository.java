package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.ClubArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubArticlesRepository extends JpaRepository<ClubArticles, Integer> {
    Page<ClubArticles> findByTitleContaining(String search, Pageable pageable);
}