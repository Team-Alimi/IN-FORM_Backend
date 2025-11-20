package goat.inform_backend.service;

import goat.inform_backend.dto.*;
import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.repository.ClubArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubArticleService {

    private final ClubArticlesRepository clubArticlesRepository;

    /**
     * 동아리 게시글 목록 조회 (검색 필터링 + 페이지네이션)
     * [GET /api/v1/club_articles?search=...&page=...]
     */
    public ClubArticlePageResponseDto getArticleList(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ClubArticles> articlePage;

        boolean hasSearch = (search != null && !search.isBlank());

        if (hasSearch) {
            // 검색 O
            articlePage = clubArticlesRepository.findByTitleContaining(search, pageable);
        } else {
            // 검색 X
            articlePage = clubArticlesRepository.findAll(pageable);
        }

        // DTO 변환
        List<ClubArticleListDto> dtoList = articlePage.getContent().stream()
                .map(ClubArticleListDto::new)
                .collect(Collectors.toList());

        // 페이지 정보 DTO 생성
        PageInfo pageInfo = new PageInfo(
                articlePage.getNumber() + 1,
                articlePage.getTotalPages(),
                articlePage.getTotalElements()
        );

        return new ClubArticlePageResponseDto(pageInfo, dtoList);
    }

    /**
     * 동아리 게시글 상세 정보 확인하기
     * [GET /api/v1/club_articles/{article_id}]
     */
    public ClubArticleDetailDto getArticleDetail(Integer articleId) {

        ClubArticles article = clubArticlesRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID=" + articleId));

        return new ClubArticleDetailDto(article);
    }

    /**
     * [특정 달 동아리 글 목록 조회]
     */
    public MonthlyClubArticlePageResponseDto getArticlesByMonth(String dateString, int page, int size) {


        YearMonth yearMonth = YearMonth.parse(dateString);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();


        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                Sort.by(Sort.Order.asc("startDate"), Sort.Order.asc("dueDate"))
        );


        Page<ClubArticles> articlePage = clubArticlesRepository.findMonthlyArticles(endOfMonth, startOfMonth, pageable);


        List<MonthlyClubArticleListDto> dtoList = articlePage.getContent().stream()
                .map(MonthlyClubArticleListDto::new)
                .collect(Collectors.toList());


        PageInfo pageInfo = new PageInfo(
                articlePage.getNumber() + 1,
                articlePage.getTotalPages(),
                articlePage.getTotalElements()
        );

        return new MonthlyClubArticlePageResponseDto(pageInfo, dtoList);
    }

    /**
     * 마감 임박 동아리 게시글 조회 (오늘 ~ 5일 뒤)
     * GET /api/v1/deadline/club_articles
     * 응답 형태가 월별 조회와 동일(첨부파일 X)하므로 Monthly DTO 재사용
     */
    public MonthlyClubArticlePageResponseDto getDeadlineArticles(int page, int size) {

        // 날짜 계산 (오늘 ~ 5일 뒤)
        LocalDate today = LocalDate.now();
        LocalDate fiveDaysLater = today.plusDays(5);

        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                Sort.by(Sort.Order.asc("dueDate"))
        );

        Page<ClubArticles> articlePage = clubArticlesRepository.findByDueDateBetween(today, fiveDaysLater, pageable);

        List<MonthlyClubArticleListDto> dtoList = articlePage.getContent().stream()
                .map(MonthlyClubArticleListDto::new)
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(
                articlePage.getNumber() + 1,
                articlePage.getTotalPages(),
                articlePage.getTotalElements()
        );

        return new MonthlyClubArticlePageResponseDto(pageInfo, dtoList);
    }
}