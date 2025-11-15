package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.Articles;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class ArticlesDetailDTO {
    private final Integer article_id;
    private final String title;
    private final String content;
    private final String original_url;
    private final LocalDate start_at;
    private final LocalDate end_at;
    private final LocalDate created;
    private final LocalDate updated;
    private final VendorResponseDTO vendors;
    private final CategoryResponseDTO categories;

    public ArticlesDetailDTO(Articles articles) {
        this.article_id = articles.getArticleId();
        this.title = articles.getTitle();
        this.content = articles.getContent();
        this.original_url = articles.getOriginalUrl();
        this.start_at = articles.getStartDate();
        this.end_at = articles.getDueDate();
        this.created = articles.getCreateDate();
        this.updated = articles.getUpdateDate();
        this.vendors = new VendorResponseDTO(articles.getVendors());
        this.categories = new CategoryResponseDTO(articles.getCategories());
    }
}
