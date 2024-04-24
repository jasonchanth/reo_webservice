package org.example.jpa_demo.event;

import org.example.jpa_demo.entity.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketCreatedEvent extends ApplicationEvent {
    private final Ticket ticket;

    public TicketCreatedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
