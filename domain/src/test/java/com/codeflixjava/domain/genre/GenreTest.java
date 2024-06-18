package com.codeflixjava.domain.genre;

import com.codeflixjava.domain.category.CategoryID;
import com.codeflixjava.domain.exceptions.NotificationException;
import com.codeflixjava.domain.validation.handler.ThrowsValidationHandler;

import com.codeflixjava.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        final var expectedActive = true;
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
        Assertions.assertEquals(false, genre.isActive());
        Assertions.assertEquals(expectedCategories, genre.getCategories().size());
        Assertions.assertEquals(actualCreated, genre.getCreatedAt());
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
        Assertions.assertNull(genre.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveGenre_whenCallUpdateWithActivate_shouldReceiveGenreUpdated(){

        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));

        final var actualGenre = Genre.newGenre("acao", false);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertFalse(actualGenre.isActive());
        Assertions.assertNotNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.update(expectedName, expectedIsActive, expectedCategories);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAValidActiveGenre_whenCallUpdateWithInactivate_shouldReceiveGenreUpdated(){

        final var expectedName = "Ação";
        final var expectedIsActive = false;
        final var expectedCategories = List.of(CategoryID.from("123"));

        final var actualGenre = Genre.newGenre("acao", true);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertTrue(actualGenre.isActive());
        Assertions.assertNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.update(expectedName, expectedIsActive, expectedCategories);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertNotNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAValidGenre_whenCallUpdateWithEmptyName_shouldReceiveNotificationException(){
        final var expectedName = " ";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualGenre = Genre.newGenre("acao", false);

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualGenre.update(expectedName, expectedIsActive, expectedCategories);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidGenre_whenCallUpdateWithNullName_shouldReceiveNotificationException(){
        final String expectedName = null;
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualGenre = Genre.newGenre("acao", false);

        final var actualException = Assertions.assertThrows(NotificationException.class, () -> {
            actualGenre.update(expectedName, expectedIsActive, expectedCategories);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidGenre_whenCallUpdateWithNullCategories_shouldReceiveOK(){
        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = new ArrayList<CategoryID>();

        final var actualGenre = Genre.newGenre("acao", expectedIsActive);

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        Assertions.assertDoesNotThrow(() -> {
            actualGenre.update(expectedName, expectedIsActive, null);
        });

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAValidEmptyCategoriesGenre_whenCallAddCategory_shouldReceiveOK(){
        final var seriesID = CategoryID.from("123");
        final var moviesID = CategoryID.from("456");

        final var expectedName = "acao";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(seriesID, moviesID);

        final var actualGenre = Genre.newGenre("acao", expectedIsActive);

        Assertions.assertEquals(0, actualGenre.getCategories().size());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.addCategory(seriesID);
        actualGenre.addCategory(moviesID);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualGenre.getUpdatedAt()));
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAInvalidNullAsCategoryID_whenCallAddCategory_shouldReceiveOK(){
        final var seriesID = CategoryID.from("123");
        final var moviesID = CategoryID.from("456");

        final var expectedName = "acao";
        final var expectedIsActive = true;
        final var expectedCategories = new ArrayList<CategoryID>();

        final var actualGenre = Genre.newGenre("acao", expectedIsActive);

        Assertions.assertEquals(0, actualGenre.getCategories().size());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.addCategory(null);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullAsCategoryID_whenCallRemoveCategory_shouldReceiveOK(){
        final var seriesID = CategoryID.from("123");
        final var moviesID = CategoryID.from("456");

        final var expectedName = "acao";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(seriesID, moviesID);

        final var actualGenre = Genre.newGenre("acao", expectedIsActive);
        actualGenre.update(expectedName, expectedIsActive, expectedCategories);

        Assertions.assertEquals(2, actualGenre.getCategories().size());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.removeCategory(null);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }
}
