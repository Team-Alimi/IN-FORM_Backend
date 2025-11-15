package goat.inform_backend.service;

import goat.inform_backend.dto.ArticlesListDTO;
import goat.inform_backend.dto.ArticlePageResponseDTO;
import goat.inform_backend.dto.PaginationDTO;
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
     * 게시글 목록보기 (페이지 + 필터링)
     * [GET /api/v1/articles?page=1&size=10&option=SCHOOL]
     */
    public ArticlePageResponseDTO getArticlesByOption(int page, int size, String option) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Articles> articlePage;
        articlePage = getArticles(option, pageable);

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



    private Page<Articles> getArticles(String option, Pageable pageable) {
        Page<Articles> articlePage;
        if (option.equalsIgnoreCase("ALL")) {
            articlePage = articlesRepository.findAll(pageable);
            return articlePage;
        }

        VendorType type = VendorType.valueOf(option.toUpperCase());
        articlePage = articlesRepository.findByVendors_VendorType(type, pageable);
        return articlePage;
    }
}