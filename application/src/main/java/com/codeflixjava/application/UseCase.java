package com.codeflixjava.application;

import com.codeflixjava.domain.category.Category;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN anIn);
}