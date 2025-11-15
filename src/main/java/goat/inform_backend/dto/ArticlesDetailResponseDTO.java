package goat.inform_backend.dto;

import lombok.Getter;

@Getter
public class ArticlesDetailResponseDTO{
    private final ArticlesDetailDTO articles;

    public ArticlesDetailResponseDTO(ArticlesDetailDTO detailData) {
        this.articles = detailData;
    }
}