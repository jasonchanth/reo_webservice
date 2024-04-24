package org.example.jpa_demo.event;

import org.example.jpa_demo.entity.Ticket;
import org.example.jpa_demo.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public UserEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUserLoginEvent(User user) {
        System.out.println("publishUserLoginEvent start");
        UserLogInEvent event = new UserLogInEvent(user);
        eventPublisher.publishEvent(event);
    }
}