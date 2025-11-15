package goat.inform_backend.entity.categories;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "categories")
public class Categories {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name",unique = true, length = 100)
    private String categoryName;
}