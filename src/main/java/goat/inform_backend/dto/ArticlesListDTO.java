package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.Articles;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class ArticlesListDTO {
    private final String title;
    private final LocalDate start_at;
    private final LocalDate end_at;
    private final LocalDate created;
    private final LocalDate updated;

    private final String vendor_name;
    private final String category_name;

    public ArticlesListDTO(Articles articles) {
        this.title = articles.getTitle();
        this.start_at = articles.getStartDate();
        this.end_at = articles.getDueDate();
        this.created = articles.getCreateDate();
        this.updated = articles.getUpdateDate();

        this.vendor_name = articles.getVendors().getVendorName();
        this.category_name = articles.getCategories().getCategoryName();
    }
}
