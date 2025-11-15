package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.Articles;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class ArticlesListDTO {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private final LocalDate createDate;
    private final LocalDate updateDate;


    private final String vendor_name;
    private final String category_name;

    public ArticlesListDTO(Articles articles) {
        this.title = articles.getTitle();
        this.startDate = articles.getStartDate();
        this.dueDate = articles.getDueDate();
        this.createDate = articles.getCreateDate();
        this.updateDate = articles.getUpdateDate();

        this.vendor_name = articles.getVendors().getVendorName();
        this.category_name = articles.getCategories().getCategoryName();
    }
}
