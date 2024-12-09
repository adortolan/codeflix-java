package com.codeflixjava.infrastructure.configuration;

import com.codeflixjava.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.codeflixjava.infrastructure.configuration.properties.ampq.QueueProperties;
import com.codeflixjava.infrastructure.services.EventService;
import com.codeflixjava.infrastructure.services.RabbitEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    @Bean
    @VideoCreatedQueue
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}