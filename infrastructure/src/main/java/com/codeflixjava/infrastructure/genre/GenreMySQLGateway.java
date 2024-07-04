package com.codeflixjava.infrastructure.genre;

import com.codeflixjava.domain.genre.Genre;
import com.codeflixjava.domain.genre.GenreGateway;
import com.codeflixjava.domain.genre.GenreID;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.pagination.SearchQuery;
import com.codeflixjava.infrastructure.genre.persistence.GenreJpaEntity;
import com.codeflixjava.infrastructure.genre.persistence.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class GenreMySQLGateway implements GenreGateway {

    private final GenreRepository genreRepository;

    public GenreMySQLGateway(final GenreRepository genreRepository) {
        this.genreRepository = Objects.requireNonNull(genreRepository);
    }

    @Override
    public Genre create(Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(GenreID anId) {

    }

    @Override
    public Optional<Genre> findById(GenreID anId) {
        return Optional.empty();
    }

    @Override
    public Genre update(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        return null;
    }

    private Genre save(final Genre aGenre) {
        return this.genreRepository.save(GenreJpaEntity.from(aGenre))
                .toAggregate();
    }
}
