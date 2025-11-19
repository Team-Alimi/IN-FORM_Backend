package goat.inform_backend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonPropertyOrder({"page_info", "articles" })
public class AllArticlePageResponseDto {
    private final PageInfo page_info;
    private final List<AllArticleListDto> articles;


    public AllArticlePageResponseDto(PageInfo pageInfo, List<AllArticleListDto> articles) {
        this.page_info = pageInfo;
        this.articles = articles;
    }
}