package com.codeflixjava.infrastructure.video;

import com.codeflixjava.domain.Identifier;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.video.*;
import com.codeflixjava.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.codeflixjava.infrastructure.services.EventService;
import com.codeflixjava.infrastructure.utils.SqlUtils;
import com.codeflixjava.infrastructure.video.persistence.VideoJpaEntity;
import com.codeflixjava.infrastructure.video.persistence.VideoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultVideoGateway implements VideoGateway {
    private final EventService eventService;
    private final VideoRepository videoRepository;
    public DefaultVideoGateway(
            @VideoCreatedQueue final EventService eventService,
            final VideoRepository videoRepository
    ) {
        this.eventService = Objects.requireNonNull(eventService);
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }
    @Override
    @Transactional
    public Video create(final Video aVideo) {
        return save(aVideo);
    }
    @Override
    public void deleteById(final VideoID anId) {
        final var aVideoId = anId.getValue();
        if (this.videoRepository.existsById(aVideoId)) {
            this.videoRepository.deleteById(aVideoId);
        }
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Video> findById(final VideoID anId) {
        return this.videoRepository.findById(anId.getValue())
                .map(VideoJpaEntity::toAggregate);
    }
    @Override
    public Video update(Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo)).toAggregate();
    }

    @Override
    public Pagination<VideoPreview> findAll(VideoSearchQuery aQuery) {
        return null;
    }

//    @Override
//    public Pagination<VideoPreview> findAll(final VideoSearchQuery aQuery) {
//        final var page = PageRequest.of(
//                aQuery.page(),
//                aQuery.perPage(),
//                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
//        );
//        final var actualPage = this.videoRepository.findAll(
//                SqlUtils.like(SqlUtils.upper(aQuery.terms())),
//                toString(aQuery.castMembers()),
//                toString(aQuery.categories()),
//                toString(aQuery.genres()),
//                page
//        );
//        return new Pagination<>(
//                actualPage.getNumber(),
//                actualPage.getSize(),
//                actualPage.getTotalElements(),
//                actualPage.toList()
//        );
//    }

    private Video save(final Video aVideo) {
        final var result = this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();

        aVideo.publishDomainEvents(this.eventService::send);
        return result;
    }

    private Set<String> toString(final Set<? extends Identifier> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        return ids.stream()
                .map(Identifier::getValue)
                .collect(Collectors.toSet());
    }
}