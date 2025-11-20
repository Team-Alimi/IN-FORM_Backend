package goat.inform_backend.controller;

import goat.inform_backend.dto.MonthlyClubArticlePageResponseDto;
import goat.inform_backend.dto.SchoolArticlePageResponseDto;
import goat.inform_backend.service.ClubArticleService;
import goat.inform_backend.service.SchoolArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deadline")
public class DeadlineController {

    private final SchoolArticleService schoolArticleService;
    private final ClubArticleService clubArticleService;

    /**
     * 마감기한 임박 학교 게시글 목록 보기 (끝나기 5일 전)
     * [GET /api/v1/deadline/school_articles?page=1&size=5]
     */
    @GetMapping("/school_articles")
    public ResponseEntity<SchoolArticlePageResponseDto> getDeadlineSchoolArticles(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        SchoolArticlePageResponseDto responseDto = schoolArticleService.getDeadlineArticles(page, size);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 마감기한 임박 동아리 게시글 목록 보기 (끝나기 5일 전)
     * [GET /api/v1/deadline/club_articles?page=1&size=5]
     */
    @GetMapping("/club_articles")
    public ResponseEntity<MonthlyClubArticlePageResponseDto> getDeadlineClubArticles(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        MonthlyClubArticlePageResponseDto responseDto = clubArticleService.getDeadlineArticles(page, size);
        return ResponseEntity.ok(responseDto);
    }
}