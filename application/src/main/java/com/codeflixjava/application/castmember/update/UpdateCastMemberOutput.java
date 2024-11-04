package com.codeflixjava.application.castmember.update;

import com.codeflixjava.domain.castmember.CastMember;

public record UpdateCastMemberOutput(String id) {
    public static UpdateCastMemberOutput from(final CastMember aMember) {
        return new UpdateCastMemberOutput(aMember.getId().getValue());
    }
}
