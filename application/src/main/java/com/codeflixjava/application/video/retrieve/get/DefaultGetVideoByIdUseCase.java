package com.codeflixjava.application.video.retrieve.get;

import com.codeflixjava.domain.exceptions.NotFoundException;
import com.codeflixjava.domain.video.Video;
import com.codeflixjava.domain.video.VideoGateway;
import com.codeflixjava.domain.video.VideoID;

import java.util.Objects;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase {
    private final VideoGateway videoGateway;
    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }
    @Override
    public VideoOutput execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        return this.videoGateway.findById(aVideoId)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, aVideoId));
    }
}