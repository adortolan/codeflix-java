package com.codeflixjava.domain.genre;

import com.codeflixjava.domain.exceptions.NotificationException;
import com.codeflixjava.domain.validation.handler.ThrowsValidationHandler;

import com.codeflixjava.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenreTest {

    @Test
    public void givenValidParams_whenCallNewGenre_shouldInstantiateAGenre(){

        final var expectedName = "Action";
        final var expectedActive = true;
        final var expectedCategories = 0;

        final var actualGenre = Genre.newGenre(expectedName, expectedActive);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories().size());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertNotNull(actualGenre.getUpdatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
   public void givenInvalidNullName_whenCallNewGenreAndValidate_shouldReceiveAError(){

        final String expectedName = null;
        final boolean expectedActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var actualExceptionError = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Genre.newGenre(expectedName, expectedActive);
                });

        Assertions.assertEquals(expectedErrorCount, actualExceptionError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualExceptionError.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewGenreAndValidate_shouldReceiveAError(){

        final String expectedName = " ";
        final boolean expectedActive = true;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;

        final var actualExceptionError = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Genre.newGenre(expectedName, expectedActive);
                });

        Assertions.assertEquals(expectedErrorCount, actualExceptionError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualExceptionError.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNameWithLengthGreaterThan255_whenCallNewGenreAndValidate_shouldReceiveAError(){

        final String expectedName = "a".repeat(256);
        final boolean expectedActive = true;
        final var expectedErrorMessage = "'name' must be between 1 and 255 characters";
        final var expectedErrorCount = 1;

        final var actualExceptionError = Assertions.assertThrows(
                NotificationException.class, () -> {
                    Genre.newGenre(expectedName, expectedActive);
                });

        Assertions.assertEquals(expectedErrorCount, actualExceptionError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualExceptionError.getErrors().get(0).message());
    }

    @Test
    public void givenAnActiveGenre_whenCallDeactivate_shouldReceiveOK(){

        final var expectedName = "Action";
        final var expectedActive = false;
        final var expectedCategories = 0;

        final var genre = Genre.newGenre(expectedName, true);

        Assertions.assertNotNull(genre);
        Assertions.assertEquals(expectedName, genre.getName());
        Assertions.assertEquals(expectedActive, genre.isActive());

        final var actualCreated = genre.getCreatedAt();
        final var actualUpdated = genre.getUpdatedAt();

        genre.deactivate();

        Assertions.assertNotNull(genre.getId());
        Assertions.assertEquals(expectedName, genre.getName());
        Assertions.assertEquals(expectedActive, genre.isActive());
        Assertions.assertEquals(expectedCategories, genre.getCategories().size());
        Assertions.assertEquals(actualCreated, genre.getCreatedAt());
        Assertions.assertTrue(actualUpdated.isBefore(genre.getUpdatedAt()));
        Assertions.assertNotNull(genre.getDeletedAt());
    }

    @Test
    public void givenAnInactiveGenre_whenCallActivate_shouldReceiveOK(){

        final var expectedName = "Action";
        final var expectedActive = true;
        final var expectedCategories = 0;

        final var genre = Genre.newGenre(expectedName, false);

        Assertions.assertNotNull(genre);
        Assertions.assertFalse(genre.isActive());
        Assertions.assertNotNull(genre.getDeletedAt());

        final var actualCreated = genre.getCreatedAt();
        final var actualUpdated = genre.getUpdatedAt();

        genre.activate();

        Assertions.assertNotNull(genre.getId());
        Assertions.assertEquals(expectedName, genre.getName());
        Assertions.assertEquals(expectedActive, genre.isActive());
        Assertions.assertEquals(expectedCategories, genre.getCategories().size());
        Assertions.assertEquals(actualCreated, genre.getCreatedAt());
        Assertions.assertTrue(actualUpdated.isBefore(genre.getUpdatedAt()));
        Assertions.assertNull(genre.getDeletedAt());
    }
}
