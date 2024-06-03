package com.codeflixjava.application.category.update;

import com.codeflixjava.domain.category.Category;
import com.codeflixjava.domain.category.CategoryGateway;
import com.codeflixjava.domain.category.CategoryID;
import com.codeflixjava.domain.exceptions.DomainException;
import com.codeflixjava.domain.exceptions.NotFoundException;
import com.codeflixjava.domain.validation.Error;
import com.codeflixjava.domain.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.API.Try;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anId).orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aCategory
                .update(aName, aDescription, isActive)
                .validate(notification);

        return notification.hasError() ?
                Either.left(notification) :
                update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
