package goat.inform_backend.controller;

import goat.inform_backend.dto.ClubArticleListDto;
import goat.inform_backend.dto.ClubArticlePageResponseDto;
import goat.inform_backend.dto.ClubArticleDetailDto;
import goat.inform_backend.dto.RandomClubArticleResponseDto;
import goat.inform_backend.service.ClubArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/club_articles")
public class ClubArticleController {

    private final ClubArticleService clubArticleService;

    /**
     * 동아리 게시글 목록 조회 (검색 필터링 + 페이지네이션)
     * [GET /api/v1/club_articles?search=...&page=...]
     */
    @GetMapping
    public ResponseEntity<ClubArticlePageResponseDto> getArticleList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "search", required = false) String search
    ) {

        ClubArticlePageResponseDto responseDto = clubArticleService.getArticleList(page, size, search);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 동아리 게시글 상세 정보 확인하기
     * [GET /api/v1/club_articles/{article_id}]
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<ClubArticleDetailDto> getArticleDetail(
            @PathVariable Integer articleId
    ) {
        ClubArticleDetailDto responseDto = clubArticleService.getArticleDetail(articleId);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 동아리 게시글 중 랜덤 5개 조회
     * [GET /api/v1/club_articles/random]
     */
    @GetMapping("/random")
    public ResponseEntity<RandomClubArticleResponseDto> getRandomArticles() {
        RandomClubArticleResponseDto responseDto = clubArticleService.getRandomArticles();
        return ResponseEntity.ok(responseDto);
    }
}