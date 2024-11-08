package com.codeflixjava.infrastructure.castmember.models;

import com.codeflixjava.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}
