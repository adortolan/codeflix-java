package com.codeflixjava.domain.video;

import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.category.CategoryID;
import com.codeflixjava.domain.genre.GenreID;

import java.util.Set;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres
) {
}
