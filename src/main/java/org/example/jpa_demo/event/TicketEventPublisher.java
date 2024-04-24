package org.example.jpa_demo.event;

import org.example.jpa_demo.entity.Ticket;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TicketEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public TicketEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishTicketCreatedEvent(Ticket ticket) {
        TicketCreatedEvent event = new TicketCreatedEvent(this, ticket);
        eventPublisher.publishEvent(event);
    }
}