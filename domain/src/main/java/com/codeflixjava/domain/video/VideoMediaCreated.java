package com.codeflixjava.domain.video;

import com.codeflixjava.domain.events.DomainEvent;
import com.codeflixjava.domain.utils.InstantUtils;

import java.time.Instant;
public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn
) implements DomainEvent {
    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}