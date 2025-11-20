package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.SchoolArticles;
import goat.inform_backend.entity.vendors.Vendors;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SchoolArticleListDto {
    private final Integer article_id;
    private final String title;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;

    private final VendorDto vendors;
    private final CategoryResponseDto categories;

    public SchoolArticleListDto(SchoolArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        this.vendors = new VendorDto(entity.getVendors());
        this.categories = new CategoryResponseDto(entity.getCategories());
    }

    @Getter
    private static class VendorDto {
        private final String vendor_name;

        public VendorDto(Vendors vendor) {
            this.vendor_name = vendor.getVendorName();
        }
    }
}