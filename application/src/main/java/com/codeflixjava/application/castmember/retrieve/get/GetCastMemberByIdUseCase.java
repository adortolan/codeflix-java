package com.codeflixjava.application.castmember.retrieve.get;

import com.codeflixjava.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase
        extends UseCase<String, CastMemberOutput>
        permits DefaultGetCastMemberByIdUseCase {
}
