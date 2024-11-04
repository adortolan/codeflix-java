package com.codeflixjava.application.castmember.retrieve.get;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.exceptions.NotFoundException;

import java.util.Objects;

public final class DefaultGetCastMemberByIdUseCase extends GetCastMemberByIdUseCase {
    private final CastMemberGateway castMemberGateway;
    public DefaultGetCastMemberByIdUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }
    @Override
    public CastMemberOutput execute(final String anIn) {
        final var aMemberId = CastMemberID.from(anIn);
        return this.castMemberGateway.findById(aMemberId)
                .map(CastMemberOutput::from)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberId));
    }
}