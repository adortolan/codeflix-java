package com.codeflixjava.infrastructure.api.controller;

import com.codeflixjava.application.category.create.CreateCategoryCommand;
import com.codeflixjava.application.category.create.CreateCategoryOutput;
import com.codeflixjava.application.category.create.CreateCategoryUseCase;
import com.codeflixjava.application.category.delete.DeleteCategoryUseCase;
import com.codeflixjava.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.codeflixjava.application.category.retrieve.list.ListCategoriesUseCase;
import com.codeflixjava.application.category.update.UpdateCategoryCommand;
import com.codeflixjava.application.category.update.UpdateCategoryOutput;
import com.codeflixjava.application.category.update.UpdateCategoryUseCase;
import com.codeflixjava.domain.pagination.SearchQuery;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.validation.handler.Notification;
import com.codeflixjava.infrastructure.api.CategoryAPI;
import com.codeflixjava.infrastructure.category.models.CategoryListResponse;
import com.codeflixjava.infrastructure.category.models.CategoryResponse;
import com.codeflixjava.infrastructure.category.models.CreateCategoryRequest;
import com.codeflixjava.infrastructure.category.models.UpdateCategoryRequest;
import com.codeflixjava.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase,
            final UpdateCategoryUseCase  updateCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final ListCategoriesUseCase listCategoriesUseCase)
    {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoriesUseCase = Objects.requireNonNull(listCategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<CategoryListResponse> listCategories(
            String search,
            int page,
            int perPage,
            String sort,
            String direction) {
        return listCategoriesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CategoryApiPresenter::present);
    }

    @Override
    public CategoryResponse getById(final String id) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public void deleteById(String id) {
        this.deleteCategoryUseCase.execute(id);
    }
}
