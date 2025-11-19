package goat.inform_backend.entity.articles;

import goat.inform_backend.entity.categories.Categories;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "school_articles")
public class SchoolArticles extends BaseArticle {

    // SchoolArticles만의 고유 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // NN
    private Categories categories;
}