package com.codeflixjava.infrastructure.api.controller;

import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.infrastructure.api.CategoryAPI;
import org.springframework.http.ResponseEntity;

public class CategoryController implements CategoryAPI {
    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
