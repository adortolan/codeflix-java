package com.codeflixjava.domain.pagination;

import java.util.List;

public record Pagination<T>(
        int total,
        int page,
        int perPage,
        List<T> items
) {
}
