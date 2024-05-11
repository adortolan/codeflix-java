package com.codeflixjava.application;

import com.codeflixjava.domain.category.Category;

public class UseCase {
    public Category execute(){
        return Category.newCategory("Video","Description", true);
    }
}