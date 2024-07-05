package com.codeflixjava.infrastructure.application;

import com.codeflixjava.JacksonTest;
import com.codeflixjava.application.category.create.CreateCategoryUseCase;
import com.codeflixjava.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@JacksonTest.IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjects() {
        Assertions.assertNotNull(useCase);
        Assertions.assertNotNull(categoryRepository);
    }
}
