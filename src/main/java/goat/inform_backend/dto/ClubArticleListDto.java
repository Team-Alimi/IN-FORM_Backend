package goat.inform_backend.dto;

import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.articles.ClubAttachment;
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
    private final List<String> attachmentUrls;

    private final VendorResponseDto vendors;

    public ClubArticleListDto(ClubArticles entity) {
        this.article_id = entity.getArticleId();
        this.title = entity.getTitle();
        this.start_date = entity.getStartDate();
        this.due_date = entity.getDueDate();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();

        this.attachmentUrls = entity.getAttachments().stream()
                .map(ClubAttachment::getAttachmentUrl)
                .collect(Collectors.toList());

        this.vendors = new VendorResponseDto(entity.getVendors());
    }
}