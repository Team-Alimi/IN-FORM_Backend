package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles,Integer>{
    
}
