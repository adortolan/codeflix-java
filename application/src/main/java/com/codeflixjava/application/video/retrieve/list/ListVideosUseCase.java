package com.codeflixjava.application.video.retrieve.list;

import com.codeflixjava.application.UseCase;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
        extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
