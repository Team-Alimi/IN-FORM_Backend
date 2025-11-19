package goat.inform_backend.service;

import goat.inform_backend.dto.PageInfo;
import goat.inform_backend.dto.SchoolArticleDetailDto;
import goat.inform_backend.dto.SchoolArticleListDto;
import goat.inform_backend.dto.SchoolArticlePageResponseDto;
import goat.inform_backend.entity.articles.SchoolArticles;
import goat.inform_backend.repository.SchoolArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolArticleService {

    private final SchoolArticlesRepository schoolArticlesRepository;
    /**
     * 학교 게시글 목록 조회 (검색/카테고리 필터링 + 페이지네이션)
     * [GET /api/v1/school_articles?search=...&category=...&page=...]
     */
    public SchoolArticlePageResponseDto getArticleList(int page, int size, String search, String category) {

        // 페이지 요청서 (JPA는 0부터 시작하므로 page - 1)
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SchoolArticles> articlePage;

        // 필터링 로직 (검색과 카테고리가 동시에 들어올 수 있음)
        boolean hasSearch = (search != null && !search.isBlank());
        boolean hasCategory = (category != null && !category.isBlank());

        if (hasSearch && hasCategory) {
            //case 1: 검색 + 카테고리 설정
            articlePage = schoolArticlesRepository.findByTitleContainingAndCategories_CategoryName(search, category, pageable);

        } else if (hasSearch) {
            // case 2: 검색만
            articlePage = schoolArticlesRepository.findByTitleContaining(search, pageable);

        } else if (hasCategory) {
            // case3 : 카테고리 분류만
            articlePage = schoolArticlesRepository.findByCategories_CategoryName(category, pageable);

        } else {
            // case 4: 검색 x, 카테고리 분류 x
            articlePage = schoolArticlesRepository.findAll(pageable);
        }

        // DTO 변환
        List<SchoolArticleListDto> dtoList = articlePage.getContent().stream()
                .map(SchoolArticleListDto::new)
                .collect(Collectors.toList());

        // 페이지정보 DTO 생성
        PageInfo pageInfo = new PageInfo(
                articlePage.getNumber() + 1,        // current_page
                articlePage.getTotalPages(),        // total_pages
                articlePage.getTotalElements()      // total_articles
        );

        return new SchoolArticlePageResponseDto(pageInfo, dtoList);
    }

    /**
     * 학교 게시글 상세 정보 확인하기
     * [GET /api/v1/school_articles/{id}]
     */
    public SchoolArticleDetailDto getArticleDetail(Integer articleId) {

        SchoolArticles article = schoolArticlesRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID=" + articleId));

        return new SchoolArticleDetailDto(article);
    }
}