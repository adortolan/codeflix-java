package com.codeflixjava.application.genre.retrieve.get;

import com.codeflixjava.domain.exceptions.NotFoundException;
import com.codeflixjava.domain.genre.Genre;
import com.codeflixjava.domain.genre.GenreGateway;
import com.codeflixjava.domain.genre.GenreID;

import java.util.Objects;

public class DefaultGetGenreByIdUseCase extends GetGenreByIdUseCase{

    private final GenreGateway genreGateway;

    public DefaultGetGenreByIdUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public GenreOutput execute(final String anIn) {
        final var aGenreId = GenreID.from(anIn);
        return this.genreGateway.findById(aGenreId)
                .map(GenreOutput::from)
                .orElseThrow(() -> NotFoundException.with(Genre.class, aGenreId));

    }
}
