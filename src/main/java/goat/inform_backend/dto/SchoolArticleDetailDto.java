package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.SchoolArticles;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SchoolArticleDetailDto {
    private final Integer article_id;
    private final String title;
    private final String content;
    private final String original_url;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;

    private final VendorResponseDto vendors;
    private final CategoryResponseDto categories;

    public SchoolArticleDetailDto(SchoolArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.original_url = entity.getOriginalUrl();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        this.vendors = new VendorResponseDto(entity.getVendors());
        this.categories = new CategoryResponseDto(entity.getCategories());
    }
}