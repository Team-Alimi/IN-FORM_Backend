package goat.inform_backend.controller;

import goat.inform_backend.dto.ClubArticlePageResponseDto;
import goat.inform_backend.service.ClubArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}