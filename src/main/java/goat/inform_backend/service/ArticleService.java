package goat.inform_backend.service;

import goat.inform_backend.dto.ArticlesListDTO;
import goat.inform_backend.dto.ArticlePageResponseDTO;
import goat.inform_backend.dto.PaginationDTO;
import goat.inform_backend.dto.ArticlesDetailResponseDTO;
import goat.inform_backend.entity.articles.Articles;
import goat.inform_backend.entity.vendors.VendorType;
import goat.inform_backend.repository.ArticlesRepository;

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
public class ArticleService {

    private final ArticlesRepository articlesRepository;
    /**
     * 게시글 목록보기 (페이지 + 필터링 + 검색)
     * [GET /api/v1/articles?page=1&size=10&option=SCHOOL]
     */
    public ArticlePageResponseDTO getArticlesByOption(int page, int size, String option, String search,String category) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Articles> articlePage = getArticles(option,search,category,pageable);

        List<ArticlesListDTO> dtoList = articlePage.getContent().stream()
                .map(ArticlesListDTO::new)
                .collect(Collectors.toList());

        // 4. ⭐️ (DTO 조립 1) '페이지 정보(PaginationDto)' 만들기
        PaginationDTO paginationDto = new PaginationDTO(
                articlePage.getNumber() + 1,
                articlePage.getTotalPages(),
                articlePage.getTotalElements()
        );

        return new ArticlePageResponseDTO(paginationDto, dtoList);
    }

    /**
     * 게시글 상세보기
     * [GET /api/v1/articles/{id}]
     */
    @Transactional(readOnly = true)
    public ArticlesDetailResponseDTO getArticleById(Integer id) {

        Articles article = articlesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new ArticlesDetailResponseDTO(article);
    }

    private Page<Articles> getArticles(String option, String search, String category,Pageable pageable) {
        boolean hasSearchString = (search != null && !search.isBlank());
        boolean hasCategory = (category != null && !category.isBlank());
        if (hasCategory) {
            return articlesRepository.findByVendors_VendorTypeAndCategories_CategoryName(
                    VendorType.SCHOOL, category, pageable
            );
        }

        if (option.equalsIgnoreCase("ALL")) {
            if (hasSearchString) {
                // 상황 1 검색O + ALL
                return articlesRepository.findByTitleContaining(search, pageable);
            }
                // 상황 2: 검색X + ALL
            return articlesRepository.findAll(pageable);
        }

        VendorType type = VendorType.valueOf(option.toUpperCase());
        if (hasSearchString) {
            // 상황 3: 검색O + (SCHOOL/CLUB)
            return articlesRepository.findByTitleContainingAndVendors_VendorType(search, type, pageable);
        }
            // (상황 4) 검색X + (SCHOOL/CLUB)
        return articlesRepository.findByVendors_VendorType(type, pageable);

    }
}