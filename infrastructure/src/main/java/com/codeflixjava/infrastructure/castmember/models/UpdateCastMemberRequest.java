package com.codeflixjava.infrastructure.castmember.models;

import com.codeflixjava.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
