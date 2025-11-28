package goat.inform_backend.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class RandomClubArticleResponseDto {
    private final List<ClubArticleListDto> club_articles;

    public RandomClubArticleResponseDto(List<ClubArticleListDto> clubArticles) {
        this.club_articles = clubArticles;
    }
}