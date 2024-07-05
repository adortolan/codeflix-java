package com.codeflixjava.infrastructure.genre.presenters;

import com.codeflixjava.application.genre.retrieve.get.GenreOutput;
import com.codeflixjava.application.genre.retrieve.list.GenreListOutput;
import com.codeflixjava.infrastructure.genre.models.GenreListResponse;
import com.codeflixjava.infrastructure.genre.models.GenreResponse;

public interface GenreApiPresenter {
    static GenreResponse present(final GenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static GenreListResponse present(final GenreListOutput output) {
        return new GenreListResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
