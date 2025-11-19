package goat.inform_backend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ClubArticlePageResponseDto {
    private final PageInfo page_info;
    
    private final List<ClubArticleListDto> club_articles;

    public ClubArticlePageResponseDto(PageInfo pageInfo, List<ClubArticleListDto> clubArticles) {
        this.page_info = pageInfo;
        this.club_articles = clubArticles;
    }
}