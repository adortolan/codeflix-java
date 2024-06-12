package com.codeflixjava.domain.exceptions;

import com.codeflixjava.domain.validation.Error;
import com.codeflixjava.domain.validation.handler.Notification;

import java.util.List;

public class NotificationException extends DomainException{
    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
