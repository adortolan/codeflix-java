package com.codeflixjava.infrastructure.api.controller;

import com.codeflixjava.domain.category.CategoryGateway;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.infrastructure.api.CategoryAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {

    private final CategoryGateway categoryGateway;

    public CategoryController(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
