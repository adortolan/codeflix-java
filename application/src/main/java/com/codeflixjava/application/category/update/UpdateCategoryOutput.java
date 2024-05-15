package com.codeflixjava.application.category.update;

import com.codeflixjava.domain.category.Category;
import com.codeflixjava.domain.category.CategoryID;

public record UpdateCategoryOutput(
        CategoryID id
) {
    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
