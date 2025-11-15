package goat.inform_backend.controller; // ⭐️ (1. 새로운 controller 패키지)

import goat.inform_backend.dto.ArticlePageResponseDTO;
import goat.inform_backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles") //request 형식
public class ArticlesController {

    private final ArticleService articleService;
    /**
     * '게시글 목록보기' API
     * 예시: GET /api/v1/articles?page=1&size=20&option=ALL
     * 예시: GET /api/v1/articles?page=2&size=20&option=CLUB
     */
    @GetMapping
    public ResponseEntity<ArticlePageResponseDTO> getArticleList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "option", defaultValue = "ALL") String option
    ){
        ArticlePageResponseDTO responseDto = articleService.getArticlesByOption(page, size, option);
        return ResponseEntity.ok(responseDto);
    }

}