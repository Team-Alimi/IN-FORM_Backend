package goat.inform_backend.dto;

import lombok.Getter;

@Getter
public class PaginationDTO {
    private final int current_page;
    private final int total_pages;
    private final long total_notices;

    public PaginationDTO(int currentPage, int totalPages, long totalNotices) {
        this.current_page = currentPage;
        this.total_pages = totalPages;
        this.total_notices = totalNotices;
    }
}