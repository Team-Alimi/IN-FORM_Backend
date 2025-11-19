package goat.inform_backend.service;

import goat.inform_backend.dto.AllArticleListDto;
import goat.inform_backend.dto.AllArticlePageResponseDto;
import goat.inform_backend.dto.PageInfo;
import goat.inform_backend.entity.articles.BaseArticle;
import goat.inform_backend.entity.articles.ClubArticles;
import goat.inform_backend.entity.articles.SchoolArticles;
import goat.inform_backend.repository.ClubArticlesRepository;
import goat.inform_backend.repository.SchoolArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AllArticleService {

    private final SchoolArticlesRepository schoolArticlesRepository;
    private final ClubArticlesRepository clubArticlesRepository;

    /**
     * 특정 달에 포함된 전체 글 목록 조회 (School + Club 합병)
     * [GET /api/v1/monthly?date=YYYY-MM&page=...&size=...]
     */
    public AllArticlePageResponseDto getArticlesByMonth(String dateString, int page, int size) {

        // YYYY-MM 문자열을 날짜 범위로 변환
        YearMonth yearMonth = YearMonth.parse(dateString);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        // 두 테이블에서 각각의 글들을 조회 (특정 날짜 사이에 포함되는지)
        List<SchoolArticles> schoolList = schoolArticlesRepository.findByStartDateLessThanEqualAndDueDateGreaterThanEqual(
                endOfMonth, startOfMonth
        );
        List<ClubArticles> clubList = clubArticlesRepository.findByStartDateLessThanEqualAndDueDateGreaterThanEqual(
                endOfMonth, startOfMonth
        );

        // 두 리스트를 합병하고 정렬 (BaseArticle로 통합)
        List<BaseArticle> combinedList = Stream.concat(schoolList.stream(), clubList.stream())
                .sorted((a, b) -> b.getArticleId().compareTo(a.getArticleId())) // PK 기준 정렬 (최신순 가정)
                .collect(Collectors.toList());


        int totalArticles = combinedList.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalArticles);

        List<BaseArticle> pagedContent = (start < totalArticles)
                ? combinedList.subList(start, end)
                : List.of();

        List<AllArticleListDto> dtoList = pagedContent.stream()
                .map(AllArticleListDto::new)
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(page, (int) Math.ceil((double) totalArticles / size), totalArticles);

        return new AllArticlePageResponseDto(pageInfo, dtoList);
    }
}