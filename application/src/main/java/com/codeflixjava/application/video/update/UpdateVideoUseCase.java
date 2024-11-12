package com.codeflixjava.application.video.update;

import com.codeflixjava.application.UseCase;

public abstract class UpdateVideoUseCase
        extends UseCase<UpdateVideoCommand, UpdateVideoOutput> {
    public abstract UpdateVideoOutput execute(UpdateVideoCommand aCommand);
}
