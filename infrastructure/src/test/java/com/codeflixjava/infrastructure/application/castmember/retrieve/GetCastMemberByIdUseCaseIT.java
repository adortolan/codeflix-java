package com.codeflixjava.infrastructure.application.castmember.retrieve;

import com.codeflixjava.application.Fixture;
import com.codeflixjava.application.castmember.retrieve.get.GetCastMemberByIdUseCase;
import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.exceptions.NotFoundException;
import com.codeflixjava.infrastructure.IntegrationTest;
import com.codeflixjava.infrastructure.castmember.CastMemberJpaEntity;
import com.codeflixjava.infrastructure.castmember.persistence.CastMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@IntegrationTest
public class GetCastMemberByIdUseCaseIT {
    @Autowired
    private GetCastMemberByIdUseCase useCase;
    @Autowired
    private CastMemberRepository castMemberRepository;
    @SpyBean
    private CastMemberGateway castMemberGateway;
    @Test
    public void givenAValidId_whenCallsGetCastMember_shouldReturnIt() {
        // given
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMembers.type();
        final var aMember = CastMember.newMember(expectedName, expectedType);
        final var expectedId = aMember.getId();
        this.castMemberRepository.saveAndFlush(CastMemberJpaEntity.from(aMember));
        Assertions.assertEquals(1, this.castMemberRepository.count());
        // when
        final var actualOutput = useCase.execute(expectedId.getValue());
        // then
        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedId.getValue(), actualOutput.id());
        Assertions.assertEquals(expectedName, actualOutput.name());
        Assertions.assertEquals(expectedType, actualOutput.type());
        Assertions.assertEquals(aMember.getCreatedAt(), actualOutput.createdAt());
        Assertions.assertEquals(aMember.getUpdatedAt(), actualOutput.updatedAt());
        verify(castMemberGateway).findById(any());
    }
    @Test
    public void givenAInvalidId_whenCallsGetCastMemberAndDoesNotExists_shouldReturnNotFoundException() {
        // given
        final var expectedId = CastMemberID.from("123");
        final var expectedErrorMessage = "CastMember with ID 123 was not found";
        // when
        final var actualOutput = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.execute(expectedId.getValue());
        });
        // then
        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedErrorMessage, actualOutput.getMessage());
        verify(castMemberGateway).findById(eq(expectedId));
    }
}
