package com.codeflixjava.application.castmember.retrieve.list;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberType;

import java.time.Instant;
public record CastMemberListOutput(
        String id,
        String name,
        CastMemberType type,
        Instant createdAt
) {
    public static CastMemberListOutput from(final CastMember aMember) {
        return new CastMemberListOutput(
                aMember.getId().getValue(),
                aMember.getName(),
                aMember.getType(),
                aMember.getCreatedAt()
        );
    }
}
