package com.codeflixjava.infrastructure.configuration.usecases;

import com.codeflixjava.application.category.create.CreateCategoryUseCase;
import com.codeflixjava.application.category.create.DefaultCreateCategoryUseCase;
import com.codeflixjava.application.category.delete.DefaultDeleteCategoryUseCase;
import com.codeflixjava.application.category.delete.DeleteCategoryUseCase;
import com.codeflixjava.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.codeflixjava.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.codeflixjava.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.codeflixjava.application.category.retrieve.list.ListCategoriesUseCase;
import com.codeflixjava.application.category.update.DefaultUpdateCategoryUseCase;
import com.codeflixjava.application.category.update.UpdateCategoryUseCase;
import com.codeflixjava.domain.category.CategoryGateway;
import com.codeflixjava.infrastructure.category.persistence.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(CategoryGateway categoryRepository) {
        this.categoryGateway = categoryRepository;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
}
