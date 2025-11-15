package goat.inform_backend.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class ArticlePageResponseDTO {

    private final PaginationDTO pages;
    private final List<ArticlesListDTO> articles;

    public ArticlePageResponseDTO(PaginationDTO pages, List<ArticlesListDTO> articles) {
        this.pages = pages;
        this.articles = articles;
    }
}