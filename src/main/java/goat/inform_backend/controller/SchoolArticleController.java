package goat.inform_backend.controller;

import goat.inform_backend.dto.SchoolArticleDetailDto;
import goat.inform_backend.dto.SchoolArticlePageResponseDto;
import goat.inform_backend.service.SchoolArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school_articles")
public class SchoolArticleController {

    private final SchoolArticleService schoolArticleService;
    /**
     * 학교 게시글 목록 조회 (검색/카테고리 필터링 + 페이지네이션)
     * [GET /api/v1/school_articles?search=...&category=...&page=...]
     */
    @GetMapping
    public ResponseEntity<SchoolArticlePageResponseDto> getArticleList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "category", required = false) String category
    ) {
        SchoolArticlePageResponseDto responseDto = schoolArticleService.getArticleList(page, size, search, category);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<SchoolArticleDetailDto> getArticleDetail(
            @PathVariable Integer articleId
    ) {
        SchoolArticleDetailDto responseDto = schoolArticleService.getArticleDetail(articleId);
        return ResponseEntity.ok(responseDto);
    }
}