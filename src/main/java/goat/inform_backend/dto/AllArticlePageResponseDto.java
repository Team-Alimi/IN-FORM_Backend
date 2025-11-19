package goat.inform_backend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AllArticlePageResponseDto {
    private final PageInfo page_info;
    private final List<AllArticleListDto> articles;

    public AllArticlePageResponseDto(PageInfo pageInfo, List<AllArticleListDto> articles) {
        this.page_info = pageInfo;
        this.articles = articles;
    }
}