package com.codeflixjava.application.video.create;

import com.codeflixjava.domain.video.Video;

public record CreateVideoOutput(String id) {
    public static CreateVideoOutput from(final Video aVideo) {
        return new CreateVideoOutput(aVideo.getId().getValue());
    }
}
