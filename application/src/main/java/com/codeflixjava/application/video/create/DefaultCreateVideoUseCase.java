package com.codeflixjava.application.video.create;

import com.codeflixjava.domain.Identifier;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.category.CategoryGateway;
import com.codeflixjava.domain.category.CategoryID;
import com.codeflixjava.domain.exceptions.DomainException;
import com.codeflixjava.domain.exceptions.NotificationException;
import com.codeflixjava.domain.genre.GenreGateway;
import com.codeflixjava.domain.genre.GenreID;
import com.codeflixjava.domain.validation.Error;
import com.codeflixjava.domain.validation.ValidationHandler;
import com.codeflixjava.domain.validation.handler.Notification;
import com.codeflixjava.domain.video.Rating;
import com.codeflixjava.domain.video.Video;
import com.codeflixjava.domain.video.VideoGateway;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
public class DefaultCreateVideoUseCase extends CreateVideoUseCase {
    private final CategoryGateway categoryGateway;
    private final CastMemberGateway castMemberGateway;
    private final GenreGateway genreGateway;
    private final VideoGateway videoGateway;
    public DefaultCreateVideoUseCase(
            final CategoryGateway categoryGateway,
            final CastMemberGateway castMemberGateway,
            final GenreGateway genreGateway,
            final VideoGateway videoGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }
    @Override
    public CreateVideoOutput execute(final CreateVideoCommand aCommand) {
        final var aRating = Rating.of(aCommand.rating()).orElseThrow(invalidRating(aCommand.rating()));
        final var aLaunchYear = Year.of(aCommand.launchedAt());
        final var categories = toIdentifier(aCommand.categories(), CategoryID::from);
        final var genres = toIdentifier(aCommand.genres(), GenreID::from);
        final var members = toIdentifier(aCommand.members(), CastMemberID::from);
        final var notification = Notification.create();
        notification.append(validateCategories(categories));
        notification.append(validateGenres(genres));
        notification.append(validateMembers(members));
        final var aVideo = Video.newVideo(
                aCommand.title(),
                aCommand.description(),
                aLaunchYear,
                aCommand.duration(),
                aCommand.opened(),
                aCommand.published(),
                aRating,
                categories,
                genres,
                members
        );
        aVideo.validate(notification);
        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Video", notification);
        }
        return CreateVideoOutput.from(create(aCommand, aVideo));
    }
    private Video create(final CreateVideoCommand aCommand, final Video aVideo) {
        return this.videoGateway.create(aVideo);
    }
    private ValidationHandler validateCategories(final Set<CategoryID> ids) {
        return validateAggregate("categories", ids, categoryGateway::existsByIds);
    }
    private ValidationHandler validateGenres(final Set<GenreID> ids) {
        return validateAggregate("genres", ids, genreGateway::existsByIds);
    }
    private ValidationHandler validateMembers(final Set<CastMemberID> ids) {
        return validateAggregate("cast members", ids, castMemberGateway::existsByIds);
    }
    private <T extends Identifier> ValidationHandler validateAggregate(
            final String aggregate,
            final Set<T> ids,
            final Function<Iterable<T>, List<T>> existsByIds
    ) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }
        final var retrievedIds = existsByIds.apply(ids);
        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);
            final var missingIdsMessage = missingIds.stream()
                    .map(Identifier::getValue)
                    .collect(Collectors.joining(", "));
            notification.append(new Error("Some %s could not be found: %s".formatted(aggregate, missingIdsMessage)));
        }
        return notification;
    }
    private Supplier<DomainException> invalidRating(final String rating) {
        return () -> DomainException.with(new Error("Rating not found %s".formatted(rating)));
    }
    private <T> Set<T> toIdentifier(final Set<String> ids, final Function<String, T> mapper) {
        return ids.stream()
                .map(mapper)
                .collect(Collectors.toSet());
    }
}
