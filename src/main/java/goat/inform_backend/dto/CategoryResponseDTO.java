package goat.inform_backend.dto;

import goat.inform_backend.entity.categories.Categories;
import lombok.Getter;

@Getter
public class CategoryResponseDTO {
    private final String category_name;

    public CategoryResponseDTO(Categories category) {
        this.category_name = category.getCategoryName();
    }
}