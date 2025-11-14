package com.example.myproject.service;

import com.example.myproject.dto.ArticleResponseDto;
import com.example.myproject.entity.article.ArticleRepository;
import com.example.myproject.entity.article.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service //service임을 표시
public class ArticleService {

    @Autowired //스프링이 자동으로 ArticleRepository과 연결
    private ArticleRepository articleRepository;

    /**
     * 모든 글을 DTO에 담아 조회하기
     */
    // 읽기 전용 기능임을 명시 (성능 최적화)
    @Transactional(readOnly = true)
    public List<ArticleResponseDto> findAllArticles() {

        // 모든 글들 불러오기
        List<Article> articleList = articleRepository.findAll();

        //List<Article>를 List<ArticleResponseDto>로 변환
        return articleList.stream()
                .map(article -> new ArticleResponseDto(article)) //각 Article을 DTO로 변환
                .collect(Collectors.toList()); //다시 List로 묶기
    }

    /**
     * 2. id에 따른 글을 DTO에 담아 조회하기
     */
    @Transactional(readOnly = true)
    public ArticleResponseDto findArticleById(Integer id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new ArticleResponseDto(article);
    }
}