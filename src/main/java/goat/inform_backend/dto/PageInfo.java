package goat.inform_backend.dto;

import lombok.Getter;

@Getter
public class PageInfo {
    //
    private final int current_page;
    private final int total_pages;
    private final long total_articles;

    public PageInfo(int currentPage, int totalPages, long totalArticles) {
        this.current_page = currentPage;
        this.total_pages = totalPages;
        this.total_articles = totalArticles;
    }
}