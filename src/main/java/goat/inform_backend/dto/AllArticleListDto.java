package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.BaseArticle;
import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.articles.SchoolArticles;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AllArticleListDto {

    private final Integer article_id;
    private final String title;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;

    private final VendorResponseDto vendors;
    private final CategoryResponseDto categories; // Club 글은 null이 될 수 있음

    public AllArticleListDto(BaseArticle entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        // Vendor (VendorType 포함됨)
        this.vendors = new VendorResponseDto(entity.getVendors());

        // Categories 처리 (SchoolArticles에만 있음)
        if (entity instanceof SchoolArticles schoolArticle) {
            this.categories = new CategoryResponseDto(schoolArticle.getCategories());
        } else { // ClubArticles일 경우
            this.categories = new CategoryResponseDto(null);
        }
    }
}