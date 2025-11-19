package goat.inform_backend.dto;

import goat.inform_backend.entity.categories.Categories;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private final String category_name;

    public CategoryResponseDto(Categories category) {
        this.category_name = (category != null) ? category.getCategoryName() : null;
    }
}