package goat.inform_backend.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class MonthlyClubArticlePageResponseDto {
    private final PageInfo page_info;


    private final List<MonthlyClubArticleListDto> club_articles;

    public MonthlyClubArticlePageResponseDto(PageInfo pageInfo, List<MonthlyClubArticleListDto> clubArticles) {
        this.page_info = pageInfo;
        this.club_articles = clubArticles;
    }
}