package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.vendors.Vendors;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MonthlyClubArticleListDto {
    private final Integer article_id;
    private final String title;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;

    private final SimpleVendorDto vendors;

    public MonthlyClubArticleListDto(ClubArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        this.vendors = new SimpleVendorDto(entity.getVendors());
    }


    @Getter
    public static class SimpleVendorDto {
        private final String vendor_name;

        public SimpleVendorDto(Vendors vendor) {
            this.vendor_name = vendor.getVendorName();
        }
    }
}