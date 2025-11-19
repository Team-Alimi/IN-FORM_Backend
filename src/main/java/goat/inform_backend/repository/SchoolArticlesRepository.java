package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.SchoolArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolArticlesRepository extends JpaRepository<SchoolArticles, Integer> {

}