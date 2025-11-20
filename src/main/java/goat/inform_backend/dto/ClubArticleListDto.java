package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.articles.ClubAttachment;
import goat.inform_backend.entity.vendors.Vendors;
import lombok.Getter;

import java.time.LocalDate;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubArticleListDto {
    private final Integer article_id;
    private final String title;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;
    private final String attachment_url;

    private final VendorDto vendors;

    public ClubArticleListDto(ClubArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        this.attachment_url = entity.getAttachments().stream()
                .findFirst()
                .map(ClubAttachment::getAttachmentUrl)
                .orElse(null);

        this.vendors = new VendorDto(entity.getVendors());
    }

    @Getter
    private static class VendorDto {
        private final String vendor_name;

        public VendorDto(Vendors vendor) {
            this.vendor_name = vendor.getVendorName();
        }
    }
}