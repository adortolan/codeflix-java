package com.codeflixjava.infrastructure.video;

import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.video.*;
import com.codeflixjava.infrastructure.video.persistence.VideoJpaEntity;
import com.codeflixjava.infrastructure.video.persistence.VideoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.Optional;

public class DefaultVideoGateway implements VideoGateway {
    private final VideoRepository videoRepository;
    public DefaultVideoGateway(final VideoRepository videoRepository) {
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }
    @Override
    @Transactional
    public Video create(final Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();
    }
    @Override
    public void deleteById(VideoID anId) {
    }
    @Override
    public Optional<Video> findById(VideoID anId) {
        return Optional.empty();
    }
    @Override
    public Video update(Video aVideo) {
        return null;
    }

    @Override
    public Pagination<VideoPreview> findAll(VideoSearchQuery aQuery) {
        return null;
    }
}