package goat.inform_backend.controller; // ⭐️ (1. 새로운 controller 패키지)

import goat.inform_backend.dto.ArticlePageResponseDTO;
import goat.inform_backend.dto.ArticlesDetailDTO;
import goat.inform_backend.dto.ArticlesDetailResponseDTO;
import goat.inform_backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles") //request 형식
public class ArticlesController {

    private final ArticleService articleService;
    /**
     * 게시글 목록보기 API
     * 예시: GET /api/v1/articles?page=1&size=20&option=ALL
     * 예시: GET /api/v1/articles?page=2&size=20&option=CLUB
     */
    @GetMapping
    public ResponseEntity<ArticlePageResponseDTO> getArticleList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "option", defaultValue = "ALL") String option,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "category", required = false) String category
    ){
        ArticlePageResponseDTO responseDto = articleService.getArticlesByOption(page, size, option,search,category);
        return ResponseEntity.ok(responseDto);
    }
    /**
     * '게시글 상세보기' API
     * 예시: GET /api/v1/articles/1
     */
    @GetMapping("/{articleId}") // " /api/v1/articles/숫자 " 형태의 GET 요청
    public ResponseEntity<ArticlesDetailResponseDTO> getArticleDetail(
            @PathVariable Integer articleId
    ) {
        ArticlesDetailDTO detailData = articleService.getArticleById(articleId);
        ArticlesDetailResponseDTO response = new ArticlesDetailResponseDTO(detailData);
        return ResponseEntity.ok(response);
    }
}