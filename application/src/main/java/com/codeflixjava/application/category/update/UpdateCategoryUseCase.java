package com.codeflixjava.application.category.update;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
