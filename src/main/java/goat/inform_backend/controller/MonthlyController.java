package goat.inform_backend.controller;

import goat.inform_backend.dto.AllArticlePageResponseDto;
import goat.inform_backend.dto.MonthlyClubArticlePageResponseDto;
import goat.inform_backend.dto.SchoolArticlePageResponseDto;
import goat.inform_backend.service.AllArticleService;
import goat.inform_backend.service.ClubArticleService;
import goat.inform_backend.service.SchoolArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/monthly")
public class MonthlyController {

    private final AllArticleService allArticleService;
    private final SchoolArticleService schoolArticleService;
    private final ClubArticleService clubArticleService;

    /**
     * 특정 달에 포함된 전체 글 목록 조회 (School + Club 합병 목록)
     * [GET /api/v1/monthly?date=2025-11&page=2&size=5]
     */
    @GetMapping
    public ResponseEntity<AllArticlePageResponseDto> getCombinedArticleList(
            @RequestParam(name = "date", required = false) String date, // YYYY-MM 형식
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        if (date == null) {
            date = YearMonth.now().toString();
        }

        AllArticlePageResponseDto responseDto = allArticleService.getArticlesByMonth(date, page, size);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 특정 달에 포함된 "학교" 글 목록 조회
     * [GET /api/v1/monthly/school_articles?date=...&page=...&size=...]
     */
    @GetMapping("/school_articles")
    public ResponseEntity<SchoolArticlePageResponseDto> getSchoolArticleList(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        // date 파라미터가 없으면 현재 달 기준
        if (date == null) {
            date = YearMonth.now().toString();
        }
        SchoolArticlePageResponseDto responseDto = schoolArticleService.getArticlesByMonth(date, page, size);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 특정 달에 포함된 "동아리" 글 목록 조회
     * GET /api/v1/monthly/club_articles
     */
    @GetMapping("/club_articles")
    public ResponseEntity<MonthlyClubArticlePageResponseDto> getClubArticleList(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        // 날짜 없으면 현재 달 기준
        if (date == null) {
            date = YearMonth.now().toString();
        }

        MonthlyClubArticlePageResponseDto responseDto = clubArticleService.getArticlesByMonth(date, page, size);
        return ResponseEntity.ok(responseDto);
    }


}