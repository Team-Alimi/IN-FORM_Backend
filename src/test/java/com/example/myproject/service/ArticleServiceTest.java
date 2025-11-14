package com.example.myproject.service;

import com.example.myproject.dto.ArticleResponseDto;
import com.example.myproject.entity.article.ArticleRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
@Transactional //테스트 이후에 db원상 복구를 위함. 지금은 get하는거라 상관x, but나중 위함
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("DTO를 잘 받아왔는지 확인")
    void testFindAllArticles_ShouldReturnDtoList() {
        long initialDbCount = articleRepository.count(); // db에 몇 개 있는지 확인
        System.out.println("db에 총 " + initialDbCount + "개의 데이터가 있습니다.");

        List<ArticleResponseDto> dtoList = articleService.findAllArticles();
        assertNotNull(dtoList);
        assertEquals(initialDbCount, dtoList.size());
        
        if (!dtoList.isEmpty()) {
            ArticleResponseDto firstDto = dtoList.getFirst();
            System.out.println("조회된 DTO의 출처 이름: " + firstDto.getVendorName());
            
            assertNotNull(firstDto.getVendorName());
            assertFalse(firstDto.getVendorName().isEmpty());
        }
    }

    @Test
    @DisplayName("article_id 값으로 글을 가져온다.")
    void testFindArticleById_ShouldReturnDto() {
        Integer testId = 1;
        ArticleResponseDto dto = articleService.findArticleById(testId);

        assertNotNull(dto);
        assertEquals(testId, dto.getArticleId());
        assertEquals("첫 번째 테스트 제목", dto.getTitle());
    }

    @Test
    @DisplayName("존재하지 않는 값을 가져오려고 할 땐 예외를 발생시킨다.")
    void testFindArticleById_Exception() {
        Integer testId = 9999;

        // --- 2. 실행 & 3. 검증 (When & Then) ---
        // "주방장에게 '9999번' 요리를 달라고 했을 때,"
        // "주방장이 'IllegalArgumentException' 에러를 내는지 '검증'합니다."
        // (Service의 orElseThrow() 로직 테스트)

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, // 1. 이 에러가 터져야 함
                () -> articleService.findArticleById(testId) // 2. 이 코드를 실행했을 때
        );

        // (보너스) "에러 메시지가 우리가 설정한 게 맞는지 확인"
        assertEquals("해당 게시물이 없습니다. id=" + testId, exception.getMessage());
    }
}