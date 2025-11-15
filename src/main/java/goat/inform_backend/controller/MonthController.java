package goat.inform_backend.controller;

import goat.inform_backend.dto.ArticlesListDTO;
import goat.inform_backend.dto.ArticlePageResponseDTO;
import goat.inform_backend.dto.ArticlesListDTO;
import goat.inform_backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/monthly")
public class MonthController {
    private final ArticleService articleService;

    /**
     * '특정일 조회' API
     * 예시: GET /api/v1/monthly?date=2025-03-28&option=ALL
     */
    @GetMapping //
    public ResponseEntity<List<ArticlePageResponseDTO>> getArticlesByDate(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "option", defaultValue = "ALL") String option
    ) {
        ArticlePageResponseDTO responseDto = articleService.getArticlesByDate(date, option);

        return ResponseEntity.ok(Collections.singletonList(responseDto));
    }

}
