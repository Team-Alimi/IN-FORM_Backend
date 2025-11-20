package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.articles.ClubAttachment;
import goat.inform_backend.entity.vendors.Vendors;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubArticleDetailDto {
    // Response Param 필드 반영
    private final Integer article_id;
    private final String title;
    private final String content;
    private final String original_url;
    private final LocalDate start_date;
    private final LocalDate due_date;
    private final LocalDate created_at;
    private final LocalDate updated_at;

    //URL 전체 목록
    private final List<String> attachment_urls;

    private final VendorDto vendors;

    public ClubArticleDetailDto(ClubArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.original_url = entity.getOriginalUrl();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        // List<ClubAttachment> -> List<String> URL 목록으로 변환
        this.attachment_urls = entity.getAttachments().stream()
                .map(ClubAttachment::getAttachmentUrl)
                .collect(Collectors.toList());

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