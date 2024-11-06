package com.codeflixjava.application.castmember.retrieve.list;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
        extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
        permits DefaultListCastMembersUseCase {
}
