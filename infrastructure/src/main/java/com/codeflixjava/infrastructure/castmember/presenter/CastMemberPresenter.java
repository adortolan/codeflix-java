package com.codeflixjava.infrastructure.castmember.presenter;

import com.codeflixjava.application.castmember.retrieve.get.CastMemberOutput;
import com.codeflixjava.infrastructure.castmember.models.CastMemberResponse;

public interface CastMemberPresenter {
    static com.codeflixjava.infrastructure.castmember.models.CastMemberResponse present(final CastMemberOutput aMember) {
        return new CastMemberResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString(),
                aMember.updatedAt().toString()
        );
    }
}
