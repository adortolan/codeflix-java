package com.codeflixjava.infrastructure;
import com.codeflixjava.infrastructure.castmember.persistence.CastMemberRepository;
import com.codeflixjava.infrastructure.category.persistence.CategoryRepository;
import com.codeflixjava.infrastructure.genre.persistence.GenreRepository;
import com.codeflixjava.infrastructure.video.persistence.VideoRepository;
import org.junit.jupiter.api.extension.ExtensionContext;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

public class MySQLCleanUpExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) {
        final var appContext = SpringExtension.getApplicationContext(context);

        cleanUp(List.of(
                appContext.getBean(VideoRepository.class),
                appContext.getBean(GenreRepository.class),
                appContext.getBean(CategoryRepository.class),
                appContext.getBean(CastMemberRepository.class)
        ));
    }

    private void cleanUp(final Collection<CrudRepository> repositories) {
        repositories.forEach(CrudRepository::deleteAll);
    }
}
