package com.codeflixjava.application.category.create;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
