package com.codeflixjava.application.video.delete;

import com.codeflixjava.application.UseCaseTest;
import com.codeflixjava.domain.exceptions.InternalErrorException;
import com.codeflixjava.domain.video.VideoGateway;
import com.codeflixjava.domain.video.VideoID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteVideoUseCaseTest extends UseCaseTest {
    @InjectMocks
    private DefaultDeleteVideoUseCase useCase;
    @Mock
    private VideoGateway videoGateway;
    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway);
    }
    @Test
    public void givenAValidId_whenCallsDeleteVideo_shouldDeleteIt() {
        // given
        final var expectedId = VideoID.unique();
        doNothing()
                .when(videoGateway).deleteById(any());
        // when
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));
        // then
        verify(videoGateway).deleteById(eq(expectedId));
    }
    @Test
    public void givenAnInvalidId_whenCallsDeleteVideo_shouldBeOk() {
        // given
        final var expectedId = VideoID.from("1231");
        doNothing()
                .when(videoGateway).deleteById(any());
        // when
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));
        // then
        verify(videoGateway).deleteById(eq(expectedId));
    }
    @Test
    public void givenAValidId_whenCallsDeleteVideoAndGatewayThrowsException_shouldReceiveException() {
        // given
        final var expectedId = VideoID.from("1231");
        doThrow(InternalErrorException.with("Error on delete video", new RuntimeException()))
                .when(videoGateway).deleteById(any());
        // when
        Assertions.assertThrows(
                InternalErrorException.class,
                () -> this.useCase.execute(expectedId.getValue())
        );
        // then
        verify(videoGateway).deleteById(eq(expectedId));
    }
}