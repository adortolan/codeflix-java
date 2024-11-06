package com.codeflixjava.application.castmember.delete;

import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;

import java.util.Objects;
public final class DefaultDeleteCastMemberUseCase extends DeleteCastMemberUseCase {
    private final CastMemberGateway castMemberGateway;
    public DefaultDeleteCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }
    @Override
    public void execute(final String anIn) {
        this.castMemberGateway.deleteById(CastMemberID.from(anIn));
    }
}