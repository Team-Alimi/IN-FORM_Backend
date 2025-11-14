package com.example.myproject.dto;

import com.example.myproject.entity.article.Article;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ArticleResponseDto {

    //Article 엔티티에서 그대로 보여줄 필드
    private final Integer articleId;
    private final String title;
    private final String content;
    private final String originalUrl;
    private final LocalDate startDate;
    private final LocalDate dueDate;

    //변환해서 보여줄 필드
    private final String vendorName;
    private final String categoryName;

    public ArticleResponseDto(Article article) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.originalUrl = article.getOriginalUrl();
        this.startDate = article.getStartDate();
        this.dueDate = article.getDueDate();
        this.vendorName = article.getVendor().getVendorName();
        this.categoryName = article.getCategory().getCategoryName();
    }
}
