package com.codeflixjava.application.castmember.create;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.exceptions.NotificationException;
import com.codeflixjava.domain.validation.handler.Notification;

import java.util.Objects;
public final class DefaultCreateCastMemberUseCase extends CreateCastMemberUseCase {
    private final CastMemberGateway castMemberGateway;
    public DefaultCreateCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }
    @Override
    public CreateCastMemberOutput execute(final CreateCastMemberCommand aCommand) {
        final var aName = aCommand.name();
        final var aType = aCommand.type();
        final var notification = Notification.create();
        final var aMember = notification.validate(() -> CastMember.newMember(aName, aType));
        if (notification.hasError()) {
            notify(notification);
        }
        return CreateCastMemberOutput.from(this.castMemberGateway.create(aMember));
    }
    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate CastMember", notification);
    }
}
