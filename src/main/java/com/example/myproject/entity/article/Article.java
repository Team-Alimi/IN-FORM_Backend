package com.example.myproject.entity.article;

import jakarta.persistence.*;
import com.example.myproject.entity.category.Category;
import com.example.myproject.entity.vendor.Vendor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate; // date는 LocalDate와 매핑

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "articles")
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    @Column(nullable = false, columnDefinition = "TEXT") // text 타입 명시
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "original_url", columnDefinition = "TEXT")
    private String originalUrl;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    //다른 테이블로 연결

    @ManyToOne(fetch = FetchType.LAZY) // (다대일) Article(Many) to Vendor(One)
    @JoinColumn(name = "vendor_id", nullable = false) // 'vendor_id' 컬럼으로 조인
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY) // (다대일) Article(Many) to Category(One)
    @JoinColumn(name = "category_id", nullable = false) // 'category_id' 컬럼으로 조인
    private Category category;
}