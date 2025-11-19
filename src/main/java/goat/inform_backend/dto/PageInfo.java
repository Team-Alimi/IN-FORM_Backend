package goat.inform_backend.dto;

import lombok.Getter;

@Getter
public class PageInfo {
    //
    private final int page_info_current_page;
    private final int page_info_total_pages;
    private final long page_info_total_articles;

    public PageInfo(int currentPage, int totalPages, long totalArticles) {
        this.page_info_current_page = currentPage;
        this.page_info_total_pages = totalPages;
        this.page_info_total_articles = totalArticles;
    }
}