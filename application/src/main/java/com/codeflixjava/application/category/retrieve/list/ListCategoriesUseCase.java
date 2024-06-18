package com.codeflixjava.application.category.retrieve.list;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.pagination.SearchQuery;
import com.codeflixjava.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
