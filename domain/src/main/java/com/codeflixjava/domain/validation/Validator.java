package com.codeflixjava.domain.validation;

public abstract class Validator {
    public final ValidationHandler handler;

    public Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler(){
        return this.handler;
    }
}
