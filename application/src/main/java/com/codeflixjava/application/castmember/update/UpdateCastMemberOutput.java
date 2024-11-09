package com.codeflixjava.application.castmember.update;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberID;

public record UpdateCastMemberOutput(String id) {

    public static UpdateCastMemberOutput from(final CastMemberID anId) {
        return new UpdateCastMemberOutput(anId.getValue());
    }

    public static UpdateCastMemberOutput from(final CastMember aMember) {
        return from(aMember.getId());
    }
}
