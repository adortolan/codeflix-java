package com.codeflixjava.infrastructure.genre;

import com.codeflixjava.domain.genre.Genre;
import com.codeflixjava.domain.genre.GenreGateway;
import com.codeflixjava.domain.genre.GenreID;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.pagination.SearchQuery;

import java.util.Optional;

public class GenreMySQLGateway implements GenreGateway {
    @Override
    public Genre create(Genre aGenre) {
        return null;
    }

    @Override
    public void deleteById(GenreID anId) {

    }

    @Override
    public Optional<Genre> findById(GenreID anId) {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre aGenre) {
        return null;
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        return null;
    }
}
