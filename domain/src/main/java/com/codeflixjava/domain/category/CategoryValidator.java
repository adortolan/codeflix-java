package com.codeflixjava.domain.category;

import com.codeflixjava.domain.validation.Error;
import com.codeflixjava.domain.validation.Validator;
import com.codeflixjava.domain.validation.ValidationHandler;

public class CategoryValidator extends Validator {
    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }

}
