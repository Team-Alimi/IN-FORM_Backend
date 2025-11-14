package com.example.myproject.controller;

import com.example.myproject.dto.ArticleResponseDto;
import com.example.myproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles") // '/api/articles'로 오는 요청만 처리
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 모든 글 조회
     * GET /api/articles
     */
    @GetMapping // (RequestMapping + GetMapping)형태
    // /api/articles 로 GET 요청이 오는 경우 처리
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> dtoList = articleService.findAllArticles();
        return ResponseEntity.ok(dtoList);
    }

    /**
     * 특정 ID의 글 조회
     * GET /api/articles/1
     */
    @GetMapping("/{id}") // (RequestMapping + GetMapping) 형태
    // /api/articles/id 로 GET 요청이 오는 경우 처리
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Integer id) {
        //@PathVariable: URL의 {id} 부분을 Integer id 변수에 담기

        ArticleResponseDto dto = articleService.findArticleById(id);
        return ResponseEntity.ok(dto);
    }
}
