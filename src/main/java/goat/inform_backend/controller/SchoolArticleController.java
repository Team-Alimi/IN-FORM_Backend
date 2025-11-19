package goat.inform_backend.controller;

import goat.inform_backend.dto.SchoolArticlePageResponseDto;
import goat.inform_backend.service.SchoolArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}