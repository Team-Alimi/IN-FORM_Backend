package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.Articles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//테스트임을 명시
@SpringBootTest
class ArticleRepositoryTest {

    // 테스트할 repository 자동연결
    @Autowired
    private ArticlesRepository articleRepository;

    @Test
    @DisplayName("모든 글 get해오기")
    void testFindAllArticles() {

        System.out.println("테스트 시작: findAll() 호출");
        List<Articles> articles = articleRepository.findAll();
        System.out.println("테스트 종료: " + articles.size() + "개 조회됨");

        assertNotNull(articles);
        assertFalse(articles.isEmpty());

        System.out.println("첫 번째 Article 제목: " + articles.getFirst().getTitle());
    }
}