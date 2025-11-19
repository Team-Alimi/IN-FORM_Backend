package goat.inform_backend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SchoolArticlePageResponseDto {
    
    private final PageInfo page_info;
    private final List<SchoolArticleListDto> school_articles;

    public SchoolArticlePageResponseDto(PageInfo pageInfo, List<SchoolArticleListDto> schoolArticles) {
        this.page_info = pageInfo;
        this.school_articles = schoolArticles;
    }
}