package goat.inform_backend.entity.articles;

import goat.inform_backend.entity.categories.Categories;
import goat.inform_backend.entity.vendors.VendorType;
import goat.inform_backend.entity.vendors.Vendors;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Articles {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT") // text 타입 명시
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "original_url", columnDefinition = "TEXT")
    private String originalUrl;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private LocalDate createDate;

    @Column(name = "updated_at")
    private LocalDate updateDate;

    //다른 테이블로 연결
    @ManyToOne(fetch = FetchType.LAZY) // (다대일) Articles(Many) to Vendors(One)
    @JoinColumn(name = "vendor_id", nullable = false) // 'vendor_id' 컬럼으로 조인
    private Vendors vendors;

    @ManyToOne(fetch = FetchType.LAZY) // (다대일) Articles(Many) to Categories(One)
    @JoinColumn(name = "category_id", nullable = false) // 'category_id' 컬럼으로 조인
    private Categories categories;
}
