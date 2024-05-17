package com.codeflixjava.application.category.retrieve.list;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.category.CategorySearchQuery;
import com.codeflixjava.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
