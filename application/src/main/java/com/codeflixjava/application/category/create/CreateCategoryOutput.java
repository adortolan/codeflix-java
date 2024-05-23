package com.codeflixjava.application.category.create;

import com.codeflixjava.domain.category.Category;
import com.codeflixjava.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final CategoryID anID) {
        return new CreateCategoryOutput(anID);
    }

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
